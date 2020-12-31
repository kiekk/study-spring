package com.koreait.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.koreait.domain.UserVO;
import com.koreait.service.UserService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
public class AuthInterceptor extends HandlerInterceptorAdapter {
	
	@Setter(onMethod_ = @Autowired)
	private UserService service;
	
	private void saveDest(HttpServletRequest request) {
		String uri = request.getRequestURI();
		
		String query = request.getQueryString();
		
		if(query == null || query.equals("null")) {
			query = "";
		}else {
			query = "?" + query;
		}
		
		if(request.getMethod().equals("GET")) {
			log.info("dest : " + (uri + query));
			request.getSession().setAttribute("dest", uri + query);
		}
	}
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		
		if(session.getAttribute("login") == null) {
			log.info("current user is not logined");
			
			saveDest(request);

			Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
			
			if(loginCookie != null) {
				UserVO user = service.checkUserWithSessionKey(loginCookie.getValue());
				
				log.info("UserVO : " + user);
				
				if(user != null) {
					session.setAttribute("login", user);
					return true;
				}
			}
			response.sendRedirect("/user/login");
			return false;
		}
		return true;
	}
	
}
