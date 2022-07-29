package com.co.algomoko.challenge.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.co.algomoko.challenge.domain.ChallengeVO;
import com.co.algomoko.challenge.domain.ChallengeValidationVO;
import com.co.algomoko.challenge.domain.MyChallengeVO;
import com.co.algomoko.challenge.mapper.ChallengeMapper;

@RequestMapping("/challenge") // 기본 url 머리셋팅
@Controller
public class ChallengeController {

	@Autowired
	ChallengeMapper dao;

	// 챌린지 목록
	@GetMapping("")
	public String challenge(@RequestParam(value = "ctitle", required = false) String ctitle, Model model,
			ChallengeVO cVO) {
		// 검색
		if (ctitle != null) {
			cVO.setCtitle(ctitle);
			List<ChallengeVO> result = dao.cList(cVO);
			model.addAttribute("search", result);
		}
		List<ChallengeVO> cList = dao.cList(cVO);
		model.addAttribute("cList", cList);

		return "contents/challenge/challenge";
	}

	// 진행중인 챌린지 목록

	@GetMapping("challenging")
	public String challenging(Model model, Authentication authentication) {

		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String mid = userDetails.getUsername();

		List<MyChallengeVO> mcList = dao.mcList(mid);
		model.addAttribute("mcList", mcList);
		return "contents/challenge/challenging";
	}

	// 완료된 챌린지 목록
	@GetMapping("challengeEnd")
	public String end(Model model, Authentication at) {
		UserDetails userDetails = (UserDetails) at.getPrincipal();
		String mid = userDetails.getUsername();

		List<MyChallengeVO> eList = dao.eList(mid);
		model.addAttribute("eList", eList);
		return "contents/challenge/challengeEnd";
	}

	// 챌린지 작성페이지로 이동
	@GetMapping("cWrite")
	private String cWrite() {
		return "contents/challenge/cWrite";
	}

	// 챌린지 작성
	@PostMapping("cWrite")
	public String cInsert(@RequestParam("filename2") MultipartFile file, ChallengeVO cVO) throws Exception {
		// file == multi 스트링변환
		String projectpath = System.getProperty("user.dir") + "/src/main/resources/static/img/chl"; // user.dir은 프로젝트
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

	// 진행중인 챌린지 포기
	// @PostAuthorize("u0")
	@GetMapping("deleting")
	public String deleting(int cno2, RedirectAttributes ra, Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String mid = userDetails.getUsername();
		dao.deleting(cno2, mid);
		return "redirect:/challenge/challenging";
	}

	// 진행중인 챌린지 1개추가하는 용도
	@GetMapping("start")
	public String getStart(int cno, int cdday, MyChallengeVO mVO, Model model, RedirectAttributes ra,
			Authentication at) {
		String mid = at.getName();
		mVO.setMid(mid);

		System.out.println("아이디 : " + mid);
		System.out.println("넘어온번호 : " + cno);
		System.out.println("넘어온데이 : " + cdday);
		// 중복처리
		List<MyChallengeVO> result = dao.mcList(mid);
		int check = 0;
		for (MyChallengeVO r : result) {
			if (cno == Integer.parseInt(r.getCno())) {
				System.out.println("중복");
				check = 1;
				break;
			}
		}
		if (check == 1) {
			ra.addFlashAttribute("msg", "이미 도전중인 챌린지입니다.");
			return "redirect:/challenge";
		}
		dao.mcInsert(mid, cno, cdday);
		return "redirect:/challenge/challenging";
	}

	// 챌린지 인증
	@PostMapping("cValidation/certi")
	public String valid(int cno2, @RequestParam("vcon") String vcon, Authentication at) {
		// 로그인 ID 가져오기
		UserDetails userDetails = (UserDetails) at.getPrincipal();
		String mid = userDetails.getUsername();
		// 챌린지 인증 중복 체크
		
		// 챌린지 인증
		ChallengeValidationVO vVO = new ChallengeValidationVO();
		vVO.setCno(cno2);
		vVO.setVcon(vcon);
		vVO.setMid(mid);
		System.out.println("번호:" + vVO.getCno() + " 내용 : " + vcon + "아이디 : " + mid);		 
		dao.valid(vVO);

		// 이행률 업데이트
		ChallengeVO cddayVO = dao.getPage(cno2);
		System.out.println("전체개수 : " + cddayVO.getCdday());
		int certiCount = dao.getCertiCount(cno2, mid);
		System.out.println("인증개수 : " + certiCount);
		double cper = (double) certiCount / (double) cddayVO.getCdday() * 100;
		int ck = 0;
		System.out.println("전체개수 : " + cddayVO.getCdday() + " 인증개수 : " + certiCount + " 이행률 : "
				+ Math.round(cper * 100 / 100.0) + "%");
		dao.cperUpdate(cno2, (int) cper, mid);
		// 이행률 100% 채우면 ck가 1로 바뀌면서 완료된 챌린지로 이동 / 이미 완료된 챌린지를 새로 도전할때 생기는 중복을 ck0/1로 구분하여 방지
		if (cper != 100) {
			return "redirect:/challenge/challenging";
		}
		dao.ck(cno2, ck, mid);
		return "redirect:/challenge/challenging";
	}

	// 챌린지 인증페이지로 이동
	@GetMapping("cValidation")
	public String valid(int cno2, int mycno, Model model, Authentication at) {
		UserDetails userDetails = (UserDetails) at.getPrincipal();
		String mid = userDetails.getUsername();
		int ck = 0;
		
		model.addAttribute("getd", dao.getd(cno2, mid, ck, mycno));
		return "contents/challenge/cValidation";
	}


}
