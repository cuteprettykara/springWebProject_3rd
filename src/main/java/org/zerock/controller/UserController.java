package org.zerock.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.zerock.domain.UserVO;
import org.zerock.dto.LoginDTO;
import org.zerock.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
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
}
