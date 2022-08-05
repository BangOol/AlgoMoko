package com.co.algomoko.food.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.co.algomoko.admin.paging.PaginationUser;
import com.co.algomoko.food.domain.FoodVO;
import com.co.algomoko.food.domain.Page;
import com.co.algomoko.food.mapper.FoodMapper;

@Controller
public class FoodController {
	@Autowired
	FoodMapper dao;

//	@GetMapping("food")
//	public String food(@RequestParam(value = "ing", required = false) String ing, Model model) {
//		if (ing != null) {
//			// 검색어가 있으면 검색해서 띄우기
//			FoodVO foodVO = new FoodVO();
//			foodVO.setIng(ing);
//			List<FoodVO> result = dao.fList(foodVO);
//			model.addAttribute("fList", result);
//		}
//		// 인기 검색어 1~6등 조회수 순으로 표시
//		FoodVO foodVO3 = new FoodVO();
//		foodVO3.setIng(ing);
//		;
//		List<FoodVO> result3 = dao.pList(foodVO3);
//		model.addAttribute("pList", result3);
//		// 초기페이지는 검색없이 바로 화면띄우기
//		return "contents/food/food";
//	}
	@RequestMapping("food")
	public ModelAndView AllListView(
			@RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage,
			Map<String, Object> map, FoodVO foodVO, @RequestParam(value = "ing", defaultValue = "null") String ing)
			throws Exception {
		
			ModelAndView mav = new ModelAndView();
			
			if (foodVO.getIng() != null) {
				// 검색어가 있으면 검색해서 띄우기
				int listCnt = dao.TableCount(foodVO);
				Page page = new Page(currentPage, 4, 10);
				page.setIng(ing);
				page.setTotalRecordCount(listCnt);				
				foodVO.setFirstRecordIndex(page.getFirstRecordIndex());
				foodVO.setLastRecordIndex(page.getLastRecordIndex());
	
				mav.addObject("pagination", page);
				mav.addObject("fList", dao.fListPage(foodVO));		
			} 
		
			//인기 검색어 1~6등 조회수 순으로 표시
			List<FoodVO> result3 = dao.pList(foodVO);
			mav.addObject("pList", result3);
			mav.setViewName("contents/food/food");
			return mav;
	}

	@GetMapping("foodContents")
	public String foodContents(@RequestParam(value = "ing", required = false) String ing, Model model) {
		FoodVO foodVO = new FoodVO();
		foodVO.setIng(ing);
		FoodVO result = dao.fListOne(foodVO);
		model.addAttribute("fListOne", result);
		if (ing != null) {
			// 검색어가 있으면 검색해서 띄우기
			FoodVO foodVO2 = new FoodVO();
			foodVO2.setIng(ing);
			List<FoodVO> result2 = dao.fList(foodVO2);
			result2.remove(0);
			model.addAttribute("fList", result2);
		}
		// 음식 검색수(조회수) +1
		dao.plusView(ing);
		// 연관 검색어
		FoodVO foodVO1 = new FoodVO();
		foodVO1.setIng(ing);
		List<FoodVO> result1 = dao.rList(foodVO1);
		model.addAttribute("rList", result1);
		// 인기 검색어 1~6등 조회수 순으로 표시
		FoodVO foodVO3 = new FoodVO();
		foodVO3.setIng(ing);
		List<FoodVO> result3 = dao.pList(foodVO3);
		model.addAttribute("pList", result3);
		return "contents/food/foodContents";
	}
	
	// 음식 추가 페이지로 이동
	@GetMapping("food/fWrite")
	private String fWrite() {
		return "contents/food/fWrite";
	}
	// 음식 추가
	@PostMapping("/food/fWrite")
	private String fInsert(FoodVO fVO, HttpServletResponse response) throws IOException {
		dao.fInsert(fVO);
		String msg = "등록이 완료되었습니다";
        String url = "/food";
        response.setContentType("text/html; charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter w = response.getWriter();
        w.write("<script>alert('"+msg+"');location.href='"+url+"';</script>");
		return "contents/food/food";
	}
	
	// 음식 삭제
	@PostMapping("delete")
	public String fDelete(String ing) {
		dao.fDelete(ing);	
		return "redirect:/food";
		
	}
}
