package com.co.algomoko.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.co.algomoko.user.domain.Account;
import com.co.algomoko.user.domain.UserVO;

public interface UserService {
	public UserVO memberInfo(UserVO uservo);
	public UserVO login(UserVO uservo);
	
	//회원가입
	
	public int signup(UserVO uservo);
	

	public boolean idCheck(String mid);
	// 회원가입시 중복찾기
	public UserVO findId(String mid);
	// 로그인시 중복
	public Account findId1(String mid);
	// 아이디 찾기
	public String findId2(String mid);
	//비밀번호 암호화
	public PasswordEncoder passwordEncoder();
	//비밀번호 찾기
	
}