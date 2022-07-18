package com.co.algomoko.challenge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public String cInsert(ChallengeVO cVO) {
		dao.cInsert(cVO);
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

