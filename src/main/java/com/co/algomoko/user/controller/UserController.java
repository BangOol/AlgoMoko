package com.co.algomoko.user.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.co.algomoko.user.domain.UserVO;
import com.co.algomoko.user.email.service.EmailService;
import com.co.algomoko.user.service.UserService;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;

@Controller
public class UserController {
	@Autowired 
	UserService userService;
	@Autowired 
	EmailService emailService;
	
	//메인페이지
	@GetMapping("main")
	public String mainPage() {
		return "contents/index";
	}
	
	
	//메인페이지
	@GetMapping("index")
	public String mainPage3() {
		return "contents/index";
	}
	
	@GetMapping("myPage")
	public String mypage() {
		return "contents/user/myPage";
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
	
	
	
	
	
	//로그아웃
	@GetMapping("/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/main";
    }
	
	

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
	public String signup(@RequestParam("sex") String sex, @RequestParam("tweight") String tweight ,  UserVO vo) {
		System.out.println(vo);
		int num = userService.signup(vo);
		if(num == 1) {
			return "redirect:registerIdForm3";
		} else {
			return "contents/login/signup";
		}
	}
	
	//아디비번찾기페이지
	@GetMapping("/findAccount")
	public String findAccount() {
		return "contents/login/findAccount";
	}
	
	//아이디찾기
	@PostMapping("/findId")
	public ResponseEntity findId(@NotNull @RequestParam("uname") String uname, @RequestParam("nick") String nick, @RequestParam("birth") String birth) {
		
		String result = userService.findIdCheck(uname,nick,birth);
		System.out.println(result);
//		if(result == null)
//			return ResponseEntity.status(HttpStatus.CONFLICT).body("입력한 회원 정보를 확인해주세요");
		
		return ResponseEntity.status(HttpStatus.OK).body(result);				
	}
	
	//임시비밀번호 전송
	@PostMapping("/findPw")
	public String findPw(@RequestParam(value ="mid",required=false) String mid) throws Exception{
		System.out.println("임시비밀번호 발송:"+ mid);
		userService.sendpw(mid);
		return "contents/login/findAccount";
	}
	
	//회원정보 수정
	@GetMapping("/insertMyPage")
    public String insertMyPage() {
		return "contents/user/insertMyPage";
	}
    @PostMapping("/insertMyPage")
	public String insertMyPage2(UserVO vo) {
    	int num = userService.insertMyPage(vo);
    	System.out.println(vo);
		if(num == 1) {
			return "redirect:myPage";
		} else {
			return "contents/user/insertMyPage";
		}
    	
    }
	//비밀번호 변경
	@GetMapping("/myPageSecurityInsertPW")
	public String myPageSecurityInsertPW() {
		return "contents/user/myPageSecurityInsertPW";
	}
	
	//회원탈퇴
	@GetMapping("/myPageSecurityDeleteId")
	public String myPageSecurityDeleteId() {
		return "contents/user/myPageSecurityDeleteId";
	}
}