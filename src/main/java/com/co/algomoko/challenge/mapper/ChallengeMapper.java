package com.co.algomoko.challenge.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.co.algomoko.challenge.domain.ChallengeVO;
import com.co.algomoko.challenge.domain.ChallengeValidationVO;
import com.co.algomoko.challenge.domain.MyChallengeVO;
import com.co.algomoko.food.domain.FoodVO;

@Repository
public interface ChallengeMapper {
	// 챌린지 조회
	public ChallengeVO getPage(int cno);

	// 챌린지 인증 화면
	public List<MyChallengeVO> getd(int cno2, String mid, int ck, int mycno);

	// 챌린지 검색
	public List<ChallengeVO> cSearch(ChallengeVO cVO);

	// 챌린지 목록
	public List<ChallengeVO> cList(ChallengeVO cVO);

	// 챌린지 작성(관리자만)
	public void cInsert(ChallengeVO cVO);

	// 챌린지 수정(관리자만)
	public int cUpdate(ChallengeVO cVO);

	// 챌린지 삭제(관리자만)
	public void cDelete(int cno);

	// 진행중인 챌린지 추가
	public void mcInsert(String mid, int cno, int cdday);

	// 진행중인 챌린지 목록
	public List<MyChallengeVO> mcList(String mid);

	// 완료된 챌린지 목록
	public List<MyChallengeVO> eList(String mid);

	// 진행중인 챌린지 상세
	public ChallengeValidationVO dList(int cno);

	// 진행중인 챌린자 포기
	public void deleting(int cno2, String mid);

	// 챌린지 인증하기
	public int valid(ChallengeValidationVO vVO);

	// 오늘 일차 구하기
	public Integer getRound(int mycno, String mid);

	// 인증 갯수 구하기
	public int getCertiCount(int cno, String mid, int mycno);

	// 이행률업데이트
	public int cperUpdate(int cno, int cper, String mid, int mycno, Integer round);

	// 인증 중복 체크
	public int getDup(int cno, String cvdate, String mid, int mycno);

	// 완료된 챌린지로 이동
	public int ck(int cno, int ck, String mid, int mycno);

	// 마지막 챌린지 인증
	public Integer getLastRound(int mycno, String mid);

	// 페이징
	public List<Map<String, Object>> fListPage(ChallengeVO cVO) throws Exception;

	// count
	public int TableCount(ChallengeVO cVO) throws Exception;
}
