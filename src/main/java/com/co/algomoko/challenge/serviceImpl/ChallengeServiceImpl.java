package com.co.algomoko.challenge.serviceImpl;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.co.algomoko.challenge.domain.ChallengeVO;
import com.co.algomoko.challenge.mapper.ChallengeMapper;
import com.co.algomoko.challenge.service.ChallengeService;

public class ChallengeServiceImpl implements ChallengeService {
	@Autowired
	ChallengeMapper mapper;

	// 챌린지 목록
	@Override
	public List<ChallengeVO> cList() {
		return mapper.cList();
	}

	// 챌린지 작성
	@Override
	public void cInsert(ChallengeVO cVO, MultipartFile file) throws Exception {
		String projectpath = System.getProperty("user.dir")+"/src/main/resources/static/img/chl";
		UUID uuid = UUID.randomUUID();
		String filename = uuid+"_"+file.getOriginalFilename();
		File saveFile = new File(projectpath,filename);
		file.transferTo(saveFile);
		cVO.setFilename(filename);
		cVO.setFilepath("/img/"+filename);
		mapper.cInsert(cVO);
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
}
