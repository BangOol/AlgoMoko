package com.co.algomoko.challenge.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.co.algomoko.challenge.domain.ChallengeVO;
import com.co.algomoko.challenge.domain.MyChallengeVO;
@Repository
public interface ChallengeMapper {
	// 챌린지 조회
	public ChallengeVO getPage(int cno);
	
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
	
	// 도전하기누르면 추가
	public void mcInsert(int cno);
	
	// 진행중인 챌린지 목록
	public List<MyChallengeVO> mcList();
	
	
	// 챌린지 인증 페이지
	
	
}
