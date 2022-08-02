package com.co.algomoko.challenge.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.co.algomoko.challenge.domain.ChallengeVO;
import com.co.algomoko.challenge.domain.ChallengeValidationVO;
import com.co.algomoko.challenge.domain.MyChallengeVO;
import com.co.algomoko.challenge.mapper.ChallengeMapper;
import com.co.algomoko.food.domain.FoodVO;
import com.co.algomoko.food.domain.Page;

@RequestMapping("/challenge") // 기본 url 머리셋팅
@Controller
public class ChallengeController {

	@Autowired
	ChallengeMapper dao;
	String filepath = "D:/upload/";

	// 챌린지 목록
//	@GetMapping("")
//	public String challenge(@RequestParam(value = "ctitle", required = false) String ctitle, Model model,
//			ChallengeVO cVO) {
//		// 검색
//		if (ctitle != null) {
//			cVO.setCtitle(ctitle);
//			List<ChallengeVO> result = dao.cList(cVO);
//			model.addAttribute("search", result);
//		}
//		List<ChallengeVO> cList = dao.cList(cVO);
//		model.addAttribute("cList", cList);
//
//		return "contents/challenge/challenge";
//	}

	// 챌린지 목록
	@GetMapping("")
		public ModelAndView challenge(
				@RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage,	
				Map<String, Object> map, ChallengeVO cVO) throws Exception {
				ModelAndView mav = new ModelAndView();
			// 검색		
			if (cVO.getCtitle() != null) {
				cVO.setCtitle(cVO.getCtitle());
				int listCnt = dao.TableCount(cVO);
				Page page = new Page(currentPage, 6, 3);
				page.setTotalRecordCount(listCnt);
				cVO.setFirstRecordIndex(page.getFirstRecordIndex());
				cVO.setLastRecordIndex(page.getLastRecordIndex());
				
				mav.addObject("pagination",page);				
				mav.addObject("cList", dao.fListPage(cVO));
			}
			List<ChallengeVO> cList = dao.cList(cVO);
			mav.addObject("cList", cList);
			mav.setViewName("contents/challenge/challenge");
			return mav;
		}

	// 진행중인 챌린지 목록

	@GetMapping("challenging")
	public String challenging(Model model, Authentication at) {

		UserDetails userDetails = (UserDetails) at.getPrincipal();
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
		String projectpath = filepath + "/img/chl/";
		UUID uuid = UUID.randomUUID(); // 랜덤으로 이름 생성
		String filename = uuid + "_" + file.getOriginalFilename(); // 파일 이름은 UUID에 있는 랜덤값 + 원래 파일 이름
		File saveFile = new File(projectpath, filename); // 위에 적힌 경로에, name으로 저장
		file.transferTo(saveFile);
		cVO.setFilename(filename); // DB에 파일 이름 저장
		cVO.setFilepath("/img/chl/" + filename); // DB에 파일 경로 저장
		dao.cInsert(cVO);
		return "redirect:/challenge";
	}

	@ResponseBody
	@GetMapping("download")
	public void download(HttpServletResponse response, @RequestParam String img) throws Exception {

		try {
			String path = filepath + img; // 경로에 접근할 때 역슬래시('\') 사용

			File file = new File(path);
			response.setHeader("Content-Disposition", "attachment;filename=" + file.getName()); // 다운로드 되거나 로컬에 저장되는 용도로
																								// 쓰이는지를 알려주는 헤더

			FileInputStream fileInputStream = new FileInputStream(path); // 파일 읽어오기
			OutputStream out = response.getOutputStream();

			int read = 0;
			byte[] buffer = new byte[1024];
			while ((read = fileInputStream.read(buffer)) != -1) { // 1024바이트씩 계속 읽으면서 outputStream에 저장, -1이 나오면 더이상 읽을
																	// 파일이 없음
				out.write(buffer, 0, read);
			}

		} catch (Exception e) {
			throw new Exception("download error");
		}
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
		if (file != null && file.getSize() > 0) {
			String projectpath = filepath + "/img/chl/";
			UUID uuid = UUID.randomUUID();

			String filename = uuid + "_" + file.getOriginalFilename();
			System.out.println(filename);
			File saveFile = new File(projectpath, filename);
			file.transferTo(saveFile);
			cVO.setFilename(filename);
			cVO.setFilepath("/img/chl/" + filename);
		}
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
	public String valid(int cno2, int mycno, @RequestParam("vcon") String vcon, Authentication at,
			RedirectAttributes ra) {
		// 로그인 ID 가져오기
		UserDetails userDetails = (UserDetails) at.getPrincipal();
		String mid = userDetails.getUsername();

		// 챌린지 인증 중복 체크
		ChallengeValidationVO vVO = new ChallengeValidationVO();
		// round :
		// lstRound : 마지막으로 인증한 일차
		Integer round = dao.getRound(mycno, mid);
		Integer lastRound = dao.getLastRound(mycno, mid);
		if (round != lastRound) {
			vVO.setCno(cno2);
			vVO.setVcon(vcon);
			vVO.setMid(mid);
			vVO.setMycno(mycno);
			vVO.setRound(round);
			System.out.println("번호:" + vVO.getCno() + " 내용 : " + vcon + "아이디 : " + mid);
			// 챌린지 인증
			dao.valid(vVO);

			// 이행률 업데이트
			ChallengeVO cddayVO = dao.getPage(cno2);
			System.out.println("전체개수 : " + cddayVO.getCdday());
			int certiCount = dao.getCertiCount(cno2, mid, mycno);
			System.out.println("인증개수 : " + certiCount);
			double cper = (double) certiCount / (double) cddayVO.getCdday() * 100;
			int ck = 0;
			System.out.println("전체개수 : " + cddayVO.getCdday() + " 인증개수 : " + certiCount + " 이행률 : "
					+ Math.round(cper * 100 / 100.0) + "%");
			dao.cperUpdate(cno2, (int) cper, mid, mycno, round);
			// 이행률 100% 채우면 ck가 1로 바뀌면서 완료된 챌린지로 이동 / 이미 완료된 챌린지를 새로 도전할때 생기는 중복을 ck0/1로
			// 구분하여 방지

			if (cper != 100) {
				return "redirect:/challenge/challenging";
			}
			dao.ck(cno2, ck, mid, mycno);
		} else {
			ra.addFlashAttribute("msg", "오늘은 이미 인증하셨습니다.");
			return "redirect:/challenge/challenging";
		}
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
