package com.co.algomoko.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.co.algomoko.user.domain.UserVO;
import com.co.algomoko.user.service.UserService;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;

@Controller
public class UserController {
	@Autowired 
	UserService userService;
	
	//메인페이지
	@GetMapping("main")
	public String mainPage() {
		return "contents/index";
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
	
	@GetMapping("/login")
	public void login(HttpSession session, HttpServletRequest request) {
	    // 로그인에 실패한 경우
	    if (session.getAttribute("msg") != null) {
	        request.setAttribute("msg", session.getAttribute("msg"));
	        session.removeAttribute("msg");
	    }
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
	
	//에러페이지...
//	@GetMapping("error")
//	public String errorHand() {
//		return "error/error";
//	}


	
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
	
	//아이디 찾기 이동
//    @GetMapping("/member/find_id")
//    public void findId() {
//    }

    // REST 방식에서 값을 읽어내는 동작은 GET이다. ★ 매핑 주소 find_id 아닌 find/id으로 주는 것 주의!
//    @ResponseBody
//   
//    @GetMapping("/member/find/id")
//    public ResponseEntity<String> findId(String mid) {
//        String findmid = userService.findId2(mid);
//        if(findmid == null)
//            return ResponseEntity.status(HttpStatus.CONFLICT).body("아이디를 찾지 못했습니다.");
//        return ResponseEntity.ok(findmid);
//    }
//
//   
//    @GetMapping("/member/reset_password")
//    public void resetPassword() {
//    }

    
    


}