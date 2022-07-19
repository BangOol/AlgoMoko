package com.co.algomoko.challenge.service;

import java.util.List;

import com.co.algomoko.challenge.domain.ChallengeVO;

public interface ChallengeService {
	// 챌린지 게시글 개수
	public int cCount();

	// 챌린지 목록
	public List<ChallengeVO> cList();

	// 챌린지 작성(관리자만)
	void cInsert(ChallengeVO cVO);

	// 챌린지 수정(관리자만)
	public void cUpdate(int cno);

	// 챌린지 삭제(관리자만)
	void cDelete(Integer cno);
}
