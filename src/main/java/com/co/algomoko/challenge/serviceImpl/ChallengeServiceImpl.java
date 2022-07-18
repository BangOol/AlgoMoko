package com.co.algomoko.challenge.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.co.algomoko.challenge.domain.ChallengeVO;
import com.co.algomoko.challenge.mapper.ChallengeMapper;
import com.co.algomoko.challenge.service.ChallengeService;

public class ChallengeServiceImpl implements ChallengeService{
	@Autowired ChallengeMapper mapper;
	
	@Override
	public List<ChallengeVO> cList() {
		return mapper.cList();
	}
	@Override
	public void cInsert(ChallengeVO cVO) {
		mapper.cInsert(cVO);
	}
	@Override
	public int cUpdate(ChallengeVO cVO) {
		return mapper.cUpdate(cVO);
	}
	@Override
	public void cDelete(Integer cno) {
		mapper.cDelete(cno);
	}
	@Override
	public ChallengeVO getPage(int cno) {
		return mapper.getPage(cno);
	}

}
