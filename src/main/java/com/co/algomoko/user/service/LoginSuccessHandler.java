package com.co.algomoko.user.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
		
		 @Override
		    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
		            Authentication authentication) throws IOException, ServletException {
			
			 
			 //이동
			 SavedRequest savedRequest = new HttpSessionRequestCache().getRequest(request, response);
			 
			 
			 
			 //가려고 하는곳이 있으면 원래 있던 페이지
			 if (savedRequest != null)
				 new DefaultRedirectStrategy().sendRedirect(request, response, savedRequest.getRedirectUrl());
			 //없으면 메인페이지
			 else 
	            new DefaultRedirectStrategy().sendRedirect(request, response, "/main");
	        }
		
 }

