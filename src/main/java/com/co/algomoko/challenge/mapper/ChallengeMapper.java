package com.co.algomoko.challenge.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.co.algomoko.challenge.domain.ChallengeVO;
@Repository
public interface ChallengeMapper {
	// 챌린지 게시글 개수
	public int cCount();

	// 챌린지 목록
	public List<ChallengeVO> cList();

	// 챌린지 작성(관리자만)
	void cInsert(ChallengeVO cVO);

	// 챌린지 수정(관리자만)
	public ChallengeVO cUpdate(int cno);

	// 챌린지 삭제(관리자만)
	void cDelete(Integer cno);
	
}
