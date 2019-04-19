package org.zerock.controller;

import java.util.Date;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.WebUtils;
import org.zerock.domain.UserVO;
import org.zerock.dto.LoginDTO;
import org.zerock.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	private static final String LOGIN = "login";
	
	@Inject
	private UserService service ;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public void loginGET() {
		
	}
	
	@RequestMapping(value="/loginPost", method=RequestMethod.POST)
	public void loginPOST(LoginDTO dto, Model model, HttpSession session) throws Exception {
		UserVO vo = service.login(dto);
		
		if (vo == null) {
			return;
		}
		
		model.addAttribute("userVO", vo);
		
		/* => LoginInterceptor 로 옮기는게 나음. */
//		if (dto.isUseCookie()) {
//			int amount = 60 * 60 * 24 * 7;
//			Date sessionLimit = new Date(System.currentTimeMillis() + (1000*amount));
//			service.keepLogin(vo.getUid(), session.getId(), sessionLimit);
//		}
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		
		Object obj = session.getAttribute(LOGIN);
		
		if (obj != null) {
			UserVO userVO = (UserVO) obj;
			
			// 1. 세션 무효화
			session.removeAttribute(LOGIN);
			session.invalidate();
			
			Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
			if (loginCookie != null) {
				// 2. 쿠기의 유효 시간을 변경
				loginCookie.setPath("/");
				loginCookie.setMaxAge(0);
				response.addCookie(loginCookie);
				
				// 3. DB 갱신
				service.keepLogin(userVO.getUid(), session.getId(), new Date());
			}
		}
		
		return "/user/login";	// 로그인 페이지로 이동
	}
}
