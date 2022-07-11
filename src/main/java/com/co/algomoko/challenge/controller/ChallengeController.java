package com.co.algomoko.challenge.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChallengeController {
	
	@GetMapping("challenge")
	public String challenge() {
		return "contents/challenge/challenge";
	}
	
	@GetMapping("challengeDetail")
	public String challengeDetail() {
		return "contents/challenge/challengeDetail";
	}
	
	@GetMapping("challengeEnd")
	public String challengeEnd() {
		return "contents/challenge/challengeEnd";
	}
	
	@GetMapping("challenging")
	public String challenging() {
		return "contents/challenge/challenging";
	}
}

