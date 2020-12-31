package com.koreait.controller;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;

import com.koreait.domain.LoginDTO;
import com.koreait.domain.UserVO;
import com.koreait.service.UserService;

import lombok.Setter;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Setter(onMethod_ = @Autowired)
	private UserService service;
	
	@GetMapping("/login")
	public void loginGET(@ModelAttribute("dto") LoginDTO dto) {
		
	}
	
	@PostMapping("/loginPost")
	public void loginPOST(LoginDTO dto, HttpSession session, Model model) throws Exception {
		
		UserVO user = service.login(dto);
		
		if(user == null)
			return;
		
		model.addAttribute("userVO", user);
		
		if(dto.isUseCookie()) {
			int amount = 60 * 60 * 24 * 7;
			Date sessionLimit = new Date(System.currentTimeMillis()+ (1000*amount));
			
			service.keepLogin(user.getId(), session.getId(), sessionLimit);
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		Object obj = session.getAttribute("login");
		
		if(obj != null) {
			UserVO user = (UserVO) obj;
			
			session.removeAttribute("login");
			session.invalidate();
			
			Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
			
			if(loginCookie != null) {
				loginCookie.setPath("/");
				loginCookie.setMaxAge(0);
				response.addCookie(loginCookie);
				service.keepLogin(user.getId(), session.getId(), new Date());
			}
		}
		
		return "user/logout";
	}
}
