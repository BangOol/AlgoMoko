package com.co.algomoko.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.co.algomoko.user.domain.UserVO;
import com.co.algomoko.user.service.UserService;

@Controller
public class UserController {
	@Autowired UserService userService; 
	
	//메인페이지
	@GetMapping("main")
	public String mainPage() {
		return "contents/index";
	}
	
	
	//회원가입 과정 페이지들
	@GetMapping("/registerIdForm1")
	public String register1() {
		return "contents/login/registerIdForm1";
	}
	
	@GetMapping("/registerIdForm2")
	public String register2() {
		return "contents/login/registerIdForm2";
	}
	
	@GetMapping("/registerIdForm3")
	public String register3() {
		return "contents/login/registerIdForm3";
	}
	
	
	//로그인 페이지 이동
	@GetMapping("/login")
    public String loginPage(UserVO vo) {
		return "contents/login/login";
	}
	
	//로그인 처리
    @PostMapping("/login")
    public String login(UserVO vo) {
    	UserVO user = userService.login(vo);
    	if(user == null) {
    		return "contents/login/login";
    	}
		return "redirect:main";
    }
    //회원 가입 페이지 이동
    @GetMapping("/signup")
    public String signupPage(UserVO vo) {
    	return "contents/login/signup";
    }
    //회원가입 처리
    @PostMapping("/signup")
    public String signup(UserVO vo) {
    	
    	int num = userService.signup(vo);
    	if(num == 1) {
		return "redirect:registerIdForm3";
    	} else {
    		return "contents/login/signup";
    	}
    }



}
