package com.co.algomoko.user.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.co.algomoko.user.domain.Account;
import com.co.algomoko.user.domain.UserVO;
import com.co.algomoko.user.mapper.UserMapper;
import com.co.algomoko.user.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Autowired UserMapper mapper;
	UserService service;

	private PasswordEncoder passwordEncoder 
	= new BCryptPasswordEncoder();

	//회원정보보기
	@Override
	public UserVO  memberInfo(UserVO uservo){
		return mapper.search(uservo);

	}
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
		uservo.setU0("U0");
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
	//임시, 아이디찾기기능
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
	@Override
	public Account findId1(String mid) {
		// TODO Auto-generated method stub
		return mapper.findId1(mid);
	}
	





}