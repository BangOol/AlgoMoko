package com.co.algomoko.user.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import com.co.algomoko.user.domain.Account;
import com.co.algomoko.user.domain.UserVO;
import com.co.algomoko.user.mapper.UserMapper;
import com.co.algomoko.user.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Autowired UserMapper mapper;
	UserService service;

	 @Autowired
	 JavaMailSender emailSender;
	private PasswordEncoder passwordEncoder 
	= new BCryptPasswordEncoder();

	
	//로그인
	@Override
	public UserVO login(UserVO uservo){
		//단건조회
		UserVO user = mapper.search(uservo);
		//비밀번호일치 검사
		if(user != null && uservo.getMpw().equals(user.getMpw())) {
			return user;
		} else {
			return null;
		}
	}
	//회원가입
	public int signup(UserVO uservo){
		String encodedPassword = passwordEncoder.encode(uservo.getMpw());
		System.out.println(encodedPassword + "넘오어오나");
		uservo.setU0("u1");
		//uservo.setEnabled(false);
		uservo.setMpw(encodedPassword);
		
		//로그인이니 회원정보 단건조회...
		return mapper.insert(uservo);
	}

	//회원가입 -> 아이디중복체크
	@Override
	public boolean idCheck(String mid) {

		return !mapper.existByMid(mid);
	}
	//회원가입 중복방지기능
	@Override
	public UserVO findId(String mid) {
		// TODO Auto-generated method stub
		return mapper.findId(mid);
	}
	//비밀번호 암호화
	@Override
	public PasswordEncoder passwordEncoder() {
		// TODO Auto-generated method stub
		return this.passwordEncoder;
	}
	//로그인 중복
	@Override
	public Account findId1(String mid) {
		// TODO Auto-generated method stub
		return mapper.findId1(mid);
	}
	// 아이디 찾기
	@Override
	public String findId2(String mid) {
		// TODO Auto-generated method stub
		UserVO vo = mapper.findId(mid);
		if(vo == null)
			return null;
		return vo.getMid();
	}
	//아이디 찾기
	@Override
	public String findIdCheck(String uname, String nick, String birth) {
		
		return mapper.findIdCheck(uname,nick,birth);
	}
	
	//임시 비밀번호 전송
	@Override
	public void sendpw(String mid) throws Exception {
		
		// 임시비밀번호 랜덤하게 생성
		String pw = "";
		for (int i = 0; i < 12; i++) {
			pw += (char) ((Math.random() * 26) + 97);
		}
		System.out.println("임시비밀번호: "+pw);
		
		//메일전송
		MimeMessage message = emailSender.createMimeMessage();
				
		message.addRecipients(RecipientType.TO, mid); //보내는대상
		message.setSubject("Algomoko 임시비밀번호 전송");
		
		String msgg="";
        msgg+= "<div style='margin:100px;'>";
        msgg+= "<h1> 안녕하세요 Algomoko입니다. </h1>";
        msgg+= "<br>";
        msgg+= "<p>임시 비밀번호를 발급했습니다. 비밀번호 변경이 필요하실 경우 회원정보에서 변경하시길 바랍니다.<p>";
        msgg+= "<br>";
        msgg+= "<p>감사합니다.<p>";
        msgg+= "<br>";
        msgg+= "<div align='center' style='border:1px solid black; font-family:verdana';>";
        msgg+= "<h3 style='color:blue;'>임시비밀번호 입니다.</h3>";
        msgg+= "<div style='font-size:130%'>";
        msgg+= "CODE : <strong>";
        msgg+= pw+"</strong><div><br/> ";
        msgg+= "</div>";
        message.setText(msgg, "utf-8", "html");//내용
        message.setFrom(new InternetAddress("dlwoqls20539@gmail.com"));//보내는 사람
		
        emailSender.send(message);
        
		//예외처리
       
        //전송 후 암호화후 db에 저장.
        //UserVO vo = null;
        UserVO vo = new UserVO();
        //@SuppressWarnings("null")
        String encodepw = passwordEncoder.encode(pw);
        System.out.println("비밀번호 발급대상: " + mid);
        System.out.println("암호화: " +encodepw);
        
        vo.setMpw(encodepw);
        mapper.updatepw(mid,encodepw);
        
		
	}
	//회원정보 변경
	@Override
	public int insertMyPage(UserVO vo) {
		
		return mapper.updateMyPage(vo);
	}


	@Override
	public void deleteId(String mid) {
		mapper.delete(mid);
		
	}
	
	
	
	
	}
	





