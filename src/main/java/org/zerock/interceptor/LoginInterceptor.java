package org.zerock.interceptor;

import java.util.Date;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.zerock.domain.UserVO;
import org.zerock.service.UserService;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	private static final Logger log = LoggerFactory.getLogger(LoginInterceptor.class);
	private static final String LOGIN = "login";
	
	@Inject
	UserService service;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		
		if (session.getAttribute(LOGIN) != null) {
			log.info("clear login data before");
			session.removeAttribute(LOGIN);
		}
		
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		Object obj = modelAndView.getModelMap().get("userVO");
		
		
		// 로그인에 성공했다면
		if (obj != null) {
			log.info("new login success");
			
			HttpSession session = request.getSession();
			
			session.setAttribute(LOGIN, obj);

			UserVO userVO = (UserVO) obj;
			
			// remember me를 체크했을 경우
			if (request.getParameter("useCookie") != null) {
				log.info("remember me...............");
				
				// 쿠기 생성
				int amount = 60 * 60 * 24 * 7;
				
				Cookie loginCookie = new Cookie("loginCookie", session.getId());
				loginCookie.setPath("/");
				loginCookie.setMaxAge(amount);
				response.addCookie(loginCookie);
				
				// 쿠키 만료일자를 DB에 보관
				Date sessionLimit = new Date(System.currentTimeMillis() + (1000 * amount));
				service.keepLogin(userVO.getUid(), session.getId(), sessionLimit);
			}
			
			Object dest = session.getAttribute("dest");
			
			response.sendRedirect(dest == null ? "/sboard/list" : (String)dest);
		}
	}
}
