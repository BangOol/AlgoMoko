package com.co.algomoko.challenge.controller;

import java.io.File;
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
import com.co.algomoko.challenge.domain.MyChallengeVO;
import com.co.algomoko.challenge.mapper.ChallengeMapper;

@RequestMapping("/challenge") // 기본 url 머리셋팅
@Controller
public class ChallengeController {

	@Autowired
	ChallengeMapper dao;

	// 챌린지 검색
//	@GetMapping("ctitle")
//	public String challenge(@RequestParam("ctitle") String ctitle, Model model) {
//		if(ctitle != null) {
//			ChallengeVO cVO = new ChallengeVO();
//			cVO.setCtitle(ctitle);
//			List<ChallengeVO> result = dao.cSearch(cVO);
//			model.addAttribute("ctitle", result);
//		}
//		return "redirect:/challenge";
//	}
	// 챌린지 목록
	@GetMapping("")
	public String challenge(ChallengeVO cVO, Model model) {

		List<ChallengeVO> cList = dao.cList(cVO);
		model.addAttribute("cList", cList);

		return "contents/challenge/challenge";
	}

	// 진행중인 챌린지 목록
	/*
	 * @GetMapping("challenging") public String challenging(Model model) {
	 * List<MyChallengeVO> mcList = dao.mcList(); model.addAttribute("mcList",
	 * mcList); return "contents/challenge/challenging"; }
	 */
	// 챌린지 작성페이지로 이동
	@GetMapping("cWrite")
	private String cWrite() {
		return "contents/challenge/cWrite";
	}

	// 챌린지 작성
	@PostMapping("cWrite")
	public String cInsert(@RequestParam("filename2") MultipartFile file, ChallengeVO cVO) throws Exception { // 글 작성시
																												// file을
																												// 추가하기
																												// 위해
																												// MultipartFile
																												// 추가
		// file == multi 스트링변환
		String projectpath = System.getProperty("user.dir") + "/src/main/resources/static/img/chl"; // user.dir은 프로젝트
																									// 경로를 담아줌
		UUID uuid = UUID.randomUUID(); // 랜덤으로 이름 생성
		String filename = uuid + "_" + file.getOriginalFilename(); // 파일 이름은 UUID에 있는 랜덤값 + 원래 파일 이름
		File saveFile = new File(projectpath, filename); // 위에 적힌 경로에, name으로 저장
		file.transferTo(saveFile);
		cVO.setFilename(filename); // DB에 파일 이름 저장
		cVO.setFilepath("/img/chl/" + filename); // DB에 파일 경로 저장
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
		String projectpath = System.getProperty("user.dir") + "/src/main/resources/static/img/chl";
		UUID uuid = UUID.randomUUID();
		String filename = uuid + "_" + file.getOriginalFilename();
		File saveFile = new File(projectpath, filename);
		file.transferTo(saveFile);
		cVO.setFilename(filename);
		cVO.setFilepath("/img/chl/" + filename);
		dao.cUpdate(cVO);
		return "redirect:/challenge";
	}

	// 챌린지 삭제
	@GetMapping("delete")
	public String cDelete(int cno) {
		dao.cDelete(cno);
		return "redirect:/challenge";
	}

	// 진행중인 챌린지 1개추가하는 용도
	@GetMapping("start")
	public String getStart(int cno) {
		System.out.println("넘어온번호 : " + cno);
		dao.mcInsert(cno);
		return "contents/challenge/challenging";
	}
}
