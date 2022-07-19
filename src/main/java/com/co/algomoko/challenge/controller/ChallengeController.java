package com.co.algomoko.challenge.controller;

import java.util.List;

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

@RequestMapping("/challenge") // 기본 url 머리셋팅
@Controller
public class ChallengeController {
	
	@Autowired ChallengeMapper dao;
	
	// 챌린지 목록
	@GetMapping("")
	public String challenge(Model model) {		
		List<ChallengeVO> cList = dao.cList();
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
	public String cInsert(@RequestParam(value="filename", required=false) String filename, ChallengeVO cVO) {
		System.out.println(filename);
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
	public String cUpdate(ChallengeVO cVO) {
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
}

