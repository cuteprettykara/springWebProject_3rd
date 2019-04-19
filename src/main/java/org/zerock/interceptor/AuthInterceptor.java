package org.zerock.interceptor;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;
import org.zerock.domain.UserVO;
import org.zerock.service.UserService;

public class AuthInterceptor extends HandlerInterceptorAdapter {
	private static final Logger log = LoggerFactory.getLogger(AuthInterceptor.class);
	
	@Inject
	UserService service;
	
	private static final String LOGIN = "login";
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		
		// 세션에 로그인 정보가 없으면
		if (session.getAttribute(LOGIN) == null) {
			log.info("current user is not logined");
			
			saveDest(session, request);
			
			Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
			
			// 과거에 보관한 로그인 쿠기 정보가 있다면
			if (loginCookie != null) {
				// 만료일자 체크
				UserVO userVO = service.checkUserWithSessionkey(loginCookie.getValue());
				
				if (userVO != null) {
					session.setAttribute(LOGIN, userVO);
					return true;
				}
			}
			
			response.sendRedirect("/user/login");
			return false;
		}
		
		return true;
	}

	private void saveDest(HttpSession session, HttpServletRequest request) {
		String uri = request.getRequestURI();
		log.info("uri: {}", uri);
		
		String query = request.getQueryString();
		log.info("query: {}", query);
		
		if (query == null || query.contentEquals("null")) {
			query = "";
		} else {
			query = "?" + query;
		}
		
		if (request.getMethod().equals("GET")) {
			log.info("dest: {}", uri + query);
			session.setAttribute("dest", uri + query);
		}
		
	}
}
