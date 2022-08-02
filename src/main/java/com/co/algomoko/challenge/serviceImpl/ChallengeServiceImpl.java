package com.co.algomoko.challenge.serviceImpl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import com.co.algomoko.challenge.domain.ChallengeVO;
import com.co.algomoko.challenge.domain.ChallengeValidationVO;
import com.co.algomoko.challenge.domain.MyChallengeVO;
import com.co.algomoko.challenge.mapper.ChallengeMapper;
import com.co.algomoko.challenge.service.ChallengeService;
import com.co.algomoko.food.domain.FoodVO;

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
	public List<MyChallengeVO> mcList(String mid) {
		return mapper.mcList(mid);
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
	public void mcInsert(String mid, int cno, int cdday) {
		mapper.mcInsert(mid, cno, cdday);
	}

	// 챌린지 검색
	@Override
	public List<ChallengeVO> cSearch(ChallengeVO cVO) {
		return mapper.cSearch(cVO);
	}

	// 챌린지 인증 페이지 이동
	@Override
	public List<MyChallengeVO> getd(int cno2, String mid, int ck, int mycno) {
		return mapper.getd(cno2, mid, ck, mycno);
	}

	// 챌린지 인증
	@Override
	public int valid(ChallengeValidationVO vVO) {
		return mapper.valid(vVO);
	}

	// 일차 구하기
	@Override
	public Integer getRound(int mycno, String mid) {
		return mapper.getRound(mycno, mid);
	}

	// 인증 갯수 구하기
	@Override
	public int getCertiCount(int cno, String mid, int mycno) {
		return mapper.getCertiCount(cno, mid, mycno);
	}

	// 이행률 업데이트
	@Override
	public int cperUpdate(int cno, int cper, String mid, int mycno, Integer round) {
		return mapper.cperUpdate(cno, cper, mid, mycno, round);
	}

	// 완료된 챌린지
	@Override
	public List<MyChallengeVO> eList(String mid) {
		return mapper.eList(mid);
	}
	
	// 챌린지 인증 중복 체크
	@Override
	public int getDup(int cno, String cvdate, String mid, int mycno) {
		return mapper.getDup(cno, cvdate, mid, mycno);
	}
	
	// 진행중인 챌린지 포기
	@Override
	public void deleting(int cno2, String mid) {
		mapper.deleting(cno2, mid);
	}
	
	// 완료된 챌린지, 진행중인 챌린지 구분
	@Override
	public int ck(int cno, int ck, String mid,int mycno) {
		return mapper.ck(cno, ck, mid, mycno);
	}

	@Override
	public ChallengeValidationVO dList(int cno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getLastRound(int mycno, String mid) {
		return mapper.getLastRound(mycno, mid);
	}

	@Override
	public List<Map<String, Object>> fListPage(ChallengeVO cVO) throws Exception {
		return mapper.fListPage(cVO);
	}

	@Override
	public int TableCount(ChallengeVO cVO) throws Exception {
		return mapper.TableCount(cVO);
	}



	

}
