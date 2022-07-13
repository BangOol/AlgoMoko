package com.co.algomoko.user.service.impl;

import com.co.algomoko.user.domain.UserVO;
import com.co.algomoko.user.mapper.UserMapper;
import com.co.algomoko.user.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired UserMapper mapper;

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
	public int signup(UserVO uservo){
		//로그인이니 회원정보 단건조회...
		return mapper.insert(uservo);
	}

   
}
