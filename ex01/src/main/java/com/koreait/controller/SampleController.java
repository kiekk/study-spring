package com.koreait.controller;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.koreait.domain.SampleDTO;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/sample/*")
@Log4j
public class SampleController {
	//return 타입이 void일 경우 경로의 이름이 파일의 이름이 됩니다.
	//GET, POST 상관없이 Controller 로직을 수행한다면 method옵션을 사용하지 않습니다.
	@RequestMapping("")
	public void basic() {
		log.info("basic...");
	}
	
	@RequestMapping(value="/basic", method= {RequestMethod.GET, RequestMethod.POST})
	public void basicGet() {
		log.info("basic get...");
	}
	
	@GetMapping("/basicOnlyGet")
	public void basicGet2() {
		log.info("basic get only get...");
	}
	
	@GetMapping("/ex01")
	//외부에서 전달받은 값들을 객체로 받을 경우
	public String ex01(SampleDTO dto) {
		log.info(dto);
		//응답할 페이지에서 클래스이름과 동일한 객체명이 자동으로 전달됩니다.
		return "ex01";
	}
	
	//파라미터명과 DTO명이 다를 때
	@GetMapping("/ex02")
	public String ex02(
			@RequestParam("data1") String name,
			@RequestParam("data2") int age,
			Model model) {
		
			model.addAttribute("name", name);
			model.addAttribute("age", age);
			
			model.addAttribute(name);
			model.addAttribute(age);
		return "ex02";
	}
	
	//파라미터를 ArrayList에 담는 경우
	@GetMapping("/ex03")
	public String ex03(@RequestParam("data") ArrayList<String> datas,
			Model model) {
		log.info("datas : " + datas);
		model.addAttribute("datas", datas);
		return "/sample/ex03";
	}
	
	//파라미터를 배열에 담기
	//void타입일 경우에는 Controller의 경로에 ex04 경로를 합쳐서 jsp를 호출합니다.
	//	/sample/ex04.jsp
	@GetMapping("/ex04")
	public void ex04(@RequestParam("data") String[] datas,
			Model model) {
		log.info("datas : " + Arrays.toString(datas));
		model.addAttribute("datas", datas);
	}
	//Model(데이터 전달자)
	//Model 객체는 컨트롤러에서 생성된 데이터를 담아서 JSP에 전달하는 역할을 합니다.
	//반면 model 객체에 담기지 않은 데이터는 파라미터로 선언하더라도 화면까지 전달되지는 않습니다.
	//매번 담아주기 불편하므로 @ModelAttribute("name")을 사용해줍니다.
	
	@GetMapping("/ex05")
	public String ex05(SampleDTO dto, @ModelAttribute("gender") String gender) {
		log.info("" + dto);
		log.info("gender : " + gender);
		return "/sample/ex05";
	}
}
