package com.co.algomoko.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.co.algomoko.user.domain.UserVO;
import com.co.algomoko.user.service.UserService;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;

@Controller
public class UserController {
	@Autowired UserService userService;

	//메인페이지
	@GetMapping("main")
	public String mainPage() {
		return "contents/index";
	}
	//로그아웃시 페이지 이동
	@GetMapping("logout")
	public String mainPage2() {
		return "index";
	}
	//test
	@GetMapping("index")
	public String mainPage3() {
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
	@GetMapping("/loginForm")
	public String loginPage(UserVO vo) {
		return "contents/login/loginForm";
	}

	//로그인 처리
//    @PostMapping("/login")
//    public String login(UserVO vo) {
//        System.out.println(vo);
//        UserVO user = userService.login(vo);
//        if(user == null) {
//            return "contents/login/login";
//        }
//        return "redirect:main";
//    }


	//회원 가입 페이지 이동
	@GetMapping("/signup")
	public String signupPage(UserVO vo) {
		return "contents/login/signup";
	}

	//회원가입 중 아이디 중복 확인 처리
	@GetMapping("/registerIdForm2/id_check")

	public ResponseEntity<Boolean> idCheck(@NotNull String mid) {
		if (userService.idCheck(mid)==false)
			return ResponseEntity.status(HttpStatus.CONFLICT).body(false);
		return ResponseEntity.status(HttpStatus.OK).body(true);
		// return ResponseEntity.ok(true); -> 바로 윗 줄은 이렇게도 작성 가능하다.
	}





	//회원가입 최종 처리
	@PostMapping("/signup")
	public String signup(@RequestParam("sex") String sex, @RequestParam("tweight") String tweight ,@RequestParam("B0") String B0, @RequestParam("U0") String U0, UserVO vo) {
		System.out.println(vo);
		int num = userService.signup(vo);
		if(num == 1) {
			return "redirect:registerIdForm3";
		} else {

			return "contents/login/signup";
		}
	}



}