package com.co.algomoko.user.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.co.algomoko.user.domain.Account;
import com.co.algomoko.user.domain.UserVO;

//db개념으로...CRUD와 전체조회
@Mapper
public interface UserMapper {
	public int insert(UserVO uservo);
	public int delete(UserVO uservo);
	public int update(UserVO uservo);
	
	public UserVO search(UserVO uservo);
	public List<UserVO> userlist(UserVO uservo);

	public UserVO login(String mid);
	
	public boolean existByMid(String mid);
	public Account findId1(String mid);
	public UserVO findId(String mid);

}