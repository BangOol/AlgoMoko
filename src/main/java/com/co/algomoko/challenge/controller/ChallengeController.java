package com.co.algomoko.challenge.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.co.algomoko.challenge.domain.ChallengeVO;
import com.co.algomoko.challenge.mapper.ChallengeMapper;
import com.co.algomoko.challenge.service.ChallengeService;

@RequestMapping("/challenge") // 기본 url 머리셋팅
@Controller
public class ChallengeController {

	@Autowired
	ChallengeMapper dao;
	
	// 챌린지 목록
	@GetMapping("")
	public String challenge(Model model) {
		List<ChallengeVO> cList = dao.cList();
		System.out.println("파일경로는 :"+cList.get(1).getFilepath());
		model.addAttribute("cList", cList);
		return "contents/challenge/challenge";
	}

	// 챌린지 작성페이지로 이동
	@GetMapping("cWrite")
	private String cWrite() {
		return "contents/challenge/cWrite";
	}

	// 챌린지 작성
	@PostMapping("cWrite")
	public String cInsert(@RequestParam("filename2") MultipartFile file, ChallengeVO cVO) throws Exception {

		String projectpath = System.getProperty("user.dir")+"/src/main/resources/static/img/chl";
		UUID uuid = UUID.randomUUID();
		String filename = uuid+"_"+file.getOriginalFilename();
		File saveFile = new File(projectpath,filename);
		file.transferTo(saveFile);
		cVO.setFilename(filename);
		cVO.setFilepath("/img/chl/"+filename);
		dao.cInsert(cVO);
		return "redirect:/challenge";
	}

	// 챌린지 수정페이지로 이동
	@GetMapping("cUpdate")
	public String getPage(int cno, Model model) {
		model.addAttribute("getPage", dao.getPage(cno));
		return "contents/challenge/cUpdate";
	}

	// 챌린지 수정
	@PostMapping("cUpdate")
	public String cUpdate(@RequestParam("filename2") MultipartFile file, ChallengeVO cVO) throws Exception, Exception {
		// file == multi 스트링변환
		String projectpath = System.getProperty("user.dir")+"/src/main/resources/static/img/chl";
		UUID uuid = UUID.randomUUID();
		String filename = uuid+"_"+file.getOriginalFilename();
		File saveFile = new File(projectpath,filename);
		file.transferTo(saveFile);
		cVO.setFilename(filename);
		cVO.setFilepath("/img/chl/"+filename);
		
		dao.cUpdate(cVO);
		
		return "redirect:/challenge";
	}

	// 챌린지 삭제
	@GetMapping("delete")
	public String cDelete(int cno) {
		dao.cDelete(cno);
		return "redirect:/challenge";
	}

	@GetMapping("challengeDetail")
	public String challengeDetail() {
		return "contents/challenge/challengeDetail";
	}

	@GetMapping("challengeEnd")
	public String challengeEnd() {
		return "contents/challenge/challengeEnd";
	}

	// 진행중인 챌린지
	@GetMapping("challenging")
	public String challenging() {
		return "contents/challenge/challenging";
	}
	
	@GetMapping("start")
	public String getStart(int cno) {
		System.out.println("넘어온번호 : "+cno);
		dao.mcInsert(cno);
		// 진행중챌린지 1개추가하는 용도 컨트롤러임
		return "redirect:/challenge/challenging";
	}
}
