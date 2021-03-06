package com.co.algomoko.user.emailImpl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.co.algomoko.user.email.service.EmailService;
import com.co.algomoko.user.emailImpl.EmailServiceImpl;

@Controller
public class EmailController {
	@Autowired EmailService emailService;




	//입력받은 이메일로 보내기
	@PostMapping("email")
	@ResponseBody

		public String emailConfirm(String mid) throws Exception{
			System.out.println("전달 받은 이메일 : "+mid);
			emailService.sendSimpleMessage(mid);
			return mid;
		}



		//코드확인?
		@PostMapping("verify")
		@ResponseBody
		public int verifyCode(String code) {
			int result = 0;
			System.out.println("code : "+ code);
			System.out.println("code match : "+ EmailServiceImpl.ePw.equals(code));
			if(EmailServiceImpl.ePw.equals(code)) {
				result =1;
			}

			return result;
		}


	}