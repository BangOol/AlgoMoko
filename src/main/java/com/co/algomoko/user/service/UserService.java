package com.co.algomoko.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.co.algomoko.user.domain.Account;
import com.co.algomoko.user.domain.UserVO;

public interface UserService {
	public UserVO memberInfo(UserVO uservo);
	public UserVO login(UserVO uservo);
	public int signup(UserVO uservo);
	

	public boolean idCheck(String mid);
	public UserVO findId(String mid);
	public Account findId1(String mid);


	public PasswordEncoder passwordEncoder();
}