package com.co.algomoko.user.service;

import java.util.List;
import java.util.Optional;

import com.co.algomoko.user.domain.UserVO;

public interface UserService {
	public UserVO memberInfo(UserVO uservo);
	public UserVO login(UserVO uservo);
	public int signup(UserVO uservo);
	
	
	public boolean idCheck(String mid);
	
	

}
