package com.koreait.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import lombok.extern.log4j.Log4j;

@Log4j
public class LoginInterceptor extends HandlerInterceptorAdapter {
	private static final String LOGIN = "login";
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		HttpSession session = request.getSession();
		
		ModelMap modelMap = modelAndView.getModelMap();
		
		Object userVO = modelMap.get("userVO");
		
		if(userVO != null) {
			System.out.println("new login success");
			session.setAttribute(LOGIN, userVO);
			
			if(request.getParameter("useCookie") != null) {
				System.out.println("remember me...");
				Cookie loginCookie = new Cookie("loginCookie", session.getId());
				loginCookie.setPath("/");
				loginCookie.setMaxAge(60 * 60 * 24 * 7);
				response.addCookie(loginCookie);
			}
			//response.sendRedirect("/");
			Object dest = session.getAttribute("dest");
			response.sendRedirect(dest != null ? (String)dest : "/");
		}
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		
		if(session.getAttribute(LOGIN) != null) {
			System.out.println("clear login data before");
			session.removeAttribute(LOGIN);
		}
		
		return true;
	}
	
}
