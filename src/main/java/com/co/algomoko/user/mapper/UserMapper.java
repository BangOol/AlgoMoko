package com.co.algomoko.user.mapper;

import java.util.List;
import java.util.Optional;

import com.co.algomoko.user.domain.UserVO;

//db개념으로...CRUD와 전체조회
public interface UserMapper {
	public int insert(UserVO uservo);
	public int delete(UserVO uservo);
	public int update(UserVO uservo);
	public UserVO search(UserVO uservo);
	public List<UserVO> userlist(UserVO uservo);
	
	public boolean existByMid(String mid);
	public UserVO findId(String mid);
	
}
