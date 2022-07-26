package com.co.algomoko.challenge.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
	public String challenging(Model model) {
		List<MyChallengeVO> mcList = dao.mcList();
		model.addAttribute("mcList", mcList);
		return "contents/challenge/challenging";
	}

	// 완료된 챌린지 페이지
	@GetMapping("challengeEnd")
	public String end(Model model) {
		List<MyChallengeVO> eList = dao.eList();
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

	// 진행중인 챌린지 삭제
	//@PostAuthorize("u0")
	@GetMapping("deleting")
	public String deleting(int cno2) {
		dao.deleting(cno2);
		return "redirect:/challenge/challenging";
	}

	// 진행중인 챌린지 1개추가하는 용도
	@GetMapping("start")
	public String getStart(int cno, int cdday, Model model, RedirectAttributes ra) {
		System.out.println("넘어온번호 : " + cno);
		System.out.println("넘어온데이 : " + cdday);
		// 중복처리
		List<MyChallengeVO> result = dao.mcList();
		int check = 0;
		for (MyChallengeVO r : result) {
			if (cno == Integer.parseInt(r.getCno())) {
				System.out.println("중복");
				check = 1;
				break;
			}
		}
		if (check == 1) {
			ra.addFlashAttribute("msg", "이미 도전중입니다.");
			return "redirect:/challenge";
		}
		dao.mcInsert(cno, cdday);
		return "redirect:/challenge/challenging";
	}

	// 챌린지 인증
	@PostMapping("cValidation/certi")
	public String valid(int cno2, @RequestParam("vcon") String vcon) {

		ChallengeValidationVO vVO = new ChallengeValidationVO();
		int round = dao.getRound(cno2);

		vVO.setCno(cno2);
		vVO.setRound(round);
		vVO.setVcon(vcon);
		System.out.println("번호:" + vVO.getCno() + " 일차 : " + round + " 내용 : " + vcon);

		// 00일차 인증내역이 없으면 insert
		// 00일차 인증내역이 있으면 이미 인증했습니다
		// round랑 cno 기준으로 row을 select해옴 => null이냐 아니냐 체크
		// select * from challenge_validation where round = #{round} and cno3 = #{cno}

		// 챌린지인증
		dao.valid(vVO);
		// 이행률 업데이트
		// 인증횟수/도전일수
		// 도전일수 = cdday (challenge)
		// 인증횟수 = count(*) (challenge_valdation)
		ChallengeVO cddayVO = dao.getPage(cno2);
		System.out.println("전체개수 : " + cddayVO.getCdday());
		int certiCount = dao.getCertiCount(cno2);
		double cper = (double) certiCount / (double) cddayVO.getCdday() * 100;

		System.out.println("전체개수 : " + cddayVO.getCdday() + " 인증개수 : " + certiCount + " 이행률 : "
				+ Math.round(cper * 100 / 100.0) + "%");
		dao.cperUpdate(cno2, (int) cper);

		return "redirect:/challenge/challenging";
	}

	// 챌린지 인증페이지로 이동
	@GetMapping("cValidation")
	public String valid(int cno2, Model model) {
		model.addAttribute("getd", dao.getd(cno2));

		int round = dao.getRound(cno2);
		System.out.println("번호:" + cno2 + " 일차 : " + round);
		return "contents/challenge/cValidation";
	}

	// 챌린지 디테일
//	@GetMapping("challengeDetail")
//	public String dList(int cno, Model model) {
//		model.addAttribute("dList", dao.dList(cno));
//		return "contents/challenge/challengeDetail";
//	}

}
