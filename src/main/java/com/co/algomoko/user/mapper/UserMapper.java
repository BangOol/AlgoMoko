package com.co.algomoko.user.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.co.algomoko.user.domain.Account;
import com.co.algomoko.user.domain.UserVO;

//db개념으로...CRUD와 전체조회
@Mapper
public interface UserMapper {
	public int insert(UserVO uservo);
	public int delete(String mid);
	public int update(UserVO uservo);
	
	//회원정보 
	public UserVO search2(String mid);
	public UserVO search(UserVO uservo);
	public List<UserVO> userlist(UserVO uservo);

	//로그인
	public UserVO login(String mid);
	//로그인시 아이디체크
	public Account findId1(String mid);
	
	//아이디 중복체크(회원가입시)
	public boolean existByMid(String mid);	
	//회원가입
	public UserVO findId(String mid);
	
	//아이디찾기
	public String findIdCheck(String uname, String nick, String birth);
	
	//임시 비밀번호로 변경 
	public void updatepw(@Param("mid") String mid,@Param("mpw") String encodepw);
	//회원정보 수정
	public int updateMyPage(UserVO vo);
	//회원탈퇴
	public void deleteId(String mid);
	
	

}