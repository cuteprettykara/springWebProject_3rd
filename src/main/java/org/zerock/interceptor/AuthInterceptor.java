package org.zerock.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthInterceptor extends HandlerInterceptorAdapter {
	private static final Logger log = LoggerFactory.getLogger(AuthInterceptor.class);
	
	private static final String LOGIN = "login";
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		
		if (session.getAttribute(LOGIN) == null) {
			
			saveDest(session, request);
			
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
