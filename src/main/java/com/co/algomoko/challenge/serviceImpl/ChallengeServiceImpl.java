package com.co.algomoko.challenge.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.co.algomoko.challenge.domain.ChallengeVO;
import com.co.algomoko.challenge.domain.ChallengeValidationVO;
import com.co.algomoko.challenge.domain.MyChallengeVO;
import com.co.algomoko.challenge.mapper.ChallengeMapper;
import com.co.algomoko.challenge.service.ChallengeService;

public class ChallengeServiceImpl implements ChallengeService {
	@Autowired
	ChallengeMapper mapper;

	// 챌린지 목록
	@Override
	public List<ChallengeVO> cList(ChallengeVO cVO) {
		return mapper.cList(cVO);
	}

	// 진행중인 챌린지 목록
	@Override
	public List<MyChallengeVO> mcList() {
		return mapper.mcList();
	}

	// 챌린지 작성
	@Override
	public void cInsert(ChallengeVO cVO, MultipartFile file) throws Exception {
//		String projectpath = System.getProperty("user.dir")+"/src/main/resources/static/img/chl";
//		UUID uuid = UUID.randomUUID();
//		String filename = uuid+"_"+file.getOriginalFilename();
//		File saveFile = new File(projectpath,filename);
//		file.transferTo(saveFile);
//		cVO.setFilename(filename);
//		cVO.setFilepath("/img/chl"+filename);
//		mapper.cInsert(cVO);
	}

	// 챌린지 수정 페이지
	@Override
	public ChallengeVO getPage(int cno) {
		return mapper.getPage(cno);
	}

	// 챌린지 수정
	@Override
	public int cUpdate(ChallengeVO cVO) {
		return mapper.cUpdate(cVO);
	}

	// 챌린지 삭제
	@Override
	public void cDelete(int cno) {
		mapper.cDelete(cno);
	}

	// 챌린지 도전
	@Override
	public void mcInsert(int cno, int cdday) {
		mapper.mcInsert(cno, cdday);
	}

	// 챌린지 검색
	@Override
	public List<ChallengeVO> cSearch(ChallengeVO cVO) {
		return mapper.cSearch(cVO);
	}

	// 챌린지 인증 페이지
	@Override
	public ChallengeValidationVO dList(int cno) {
		return mapper.dList(cno);
	}

	// 챌린지 인증 페이지 이동
	@Override
	public MyChallengeVO getd(int cno2) {
		return mapper.getd(cno2);
	}

	// 챌린지 인증
	@Override
	public int valid(ChallengeValidationVO vVO) {
		return mapper.valid(vVO);
	}

	// 일차 구하기
	@Override
	public int getRound(int cno) {
		return mapper.getRound(cno);
	}

	// 인증 갯수 구하기
	@Override
	public int getCertiCount(int cno) {
		return mapper.getCertiCount(cno);
	}

	// 이행률 업데이트
	@Override
	public int cperUpdate(int cno, int cper) {
		return mapper.cperUpdate(cno, cper);
	}

	// 완료된 챌린지
	@Override
	public List<MyChallengeVO> eList() {
		return mapper.eList();
	}

	@Override
	public int getDup(int cno, int round) {
		return mapper.getDup(cno, round);
	}
	
	// 진행중인 챌린지 삭제
	@Override
	public void deleting(int cno2) {
		mapper.deleting(cno2);
	}

}
