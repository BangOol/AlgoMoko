package com.co.algomoko.food.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.co.algomoko.food.domain.FoodVO;

import com.co.algomoko.food.mapper.FoodMapper;

@Controller
public class FoodController {
	@Autowired
	FoodMapper dao;

	@GetMapping("food")
	public String food(@RequestParam(value = "ing", required = false) String ing, Model model) {		
		if (ing != null) {
			// 검색어가 있으면 검색해서 띄우기
			FoodVO foodVO = new FoodVO();
			foodVO.setIng(ing);
			List<FoodVO> result = dao.fList(foodVO);
			model.addAttribute("fList", result);
		}
		// 인기 검색어 1~6등 조회수 순으로 표시
		FoodVO foodVO3 = new FoodVO();
		foodVO3.setIng(ing);
		;
		List<FoodVO> result3 = dao.pList(foodVO3);
		model.addAttribute("pList", result3);
		// 초기페이지는 검색없이 바로 화면띄우기
		return "contents/food/food";
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
		// 인기 검색어 1~6등 조회수 순으로 표시
		FoodVO foodVO3 = new FoodVO();
		foodVO3.setIng(ing);
		;
		List<FoodVO> result3 = dao.pList(foodVO3);
		model.addAttribute("pList", result3);
		return "contents/food/foodContents";
	}
}
