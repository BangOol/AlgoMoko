package com.co.algomoko.challenge.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.co.algomoko.challenge.domain.ChallengeVO;
import com.co.algomoko.challenge.domain.ChallengeValidationVO;
import com.co.algomoko.challenge.domain.MyChallengeVO;

public interface ChallengeService {
	// 챌린지 조회
	public ChallengeVO getPage(int cno);

	public MyChallengeVO getd(int cno2);

	// 챌린지 검색
	public List<ChallengeVO> cSearch(ChallengeVO cVO);

	// 챌린지 목록
	public List<ChallengeVO> cList(ChallengeVO cVO);

	// 챌린지 작성(관리자만)
	public void cInsert(ChallengeVO cVO, MultipartFile file) throws Exception;

	// 챌린지 수정(관리자만)
	public int cUpdate(ChallengeVO cVO);

	// 챌린지 삭제(관리자만)
	public void cDelete(int cno);

	// 진행중인 챌린지 추가
	public void mcInsert(int cno, int cdday);

	// 진행중인 챌린지 목록
	public List<MyChallengeVO> mcList();

	// 진행중인 챌린지 상세
	public ChallengeValidationVO dList(int cno);

	// 챌린지 인증하기
	public int valid(ChallengeValidationVO vVO);
	
	// 오늘 일차 구하기
	public int getRound(int cno);
	
	// 인증 갯수 구하기
	public int getCertiCount(int cno);
	
	// 이행률업데이트
	public int cperUpdate(int cno, int cper);
}
