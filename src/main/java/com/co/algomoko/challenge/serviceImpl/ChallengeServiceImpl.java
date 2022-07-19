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
	public int cCount() {
		return 0;
	}
	@Override
	public void cInsert(ChallengeVO cVO) {
		mapper.cInsert(cVO);
	}
	@Override
	public void cUpdate(int cno) {
		mapper.cUpdate(cno);
	}
	@Override
	public void cDelete(Integer cno) {
		mapper.cDelete(cno);
	}

}
