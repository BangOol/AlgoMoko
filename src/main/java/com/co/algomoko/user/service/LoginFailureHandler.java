package com.co.algomoko.user.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.co.algomoko.user.domain.UserVO;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {				
		String msg;
		
		if(exception instanceof BadCredentialsException || exception instanceof InternalAuthenticationServiceException) {			
			msg = "아이디나 비밀번호가 맞지 않습니다";
		}else if(exception instanceof DisabledException) {			
			 msg = "계정이 비활성화 되었습니다 관리자에게 문의하세요";
		}else if (exception instanceof UsernameNotFoundException) {
			msg = "계정이 존재하지 않습니다. 회원가입 진행 후 로그인 해주세요";
		}else if (exception instanceof InternalAuthenticationServiceException) {
			msg = "내부적으로 발생한 시스템 문제로 인해 요청을 처리할 수 없습니다.";
		}		else {			
			 msg = "알수 없는 이유로 로그인에 실패 했습니다. 관리자에게 문의하세요";
		}
		
		 
		//request.setAttribute("msg", msg);
		//request.getRequestDispatcher(failURL).forward(request, response);
		//new DefaultRedirectStrategy().sendRedirect(request, response, "/login");
		//request.getRequestDispatcher(failURL).forward(request, response);
		 
		
	}
	

}
