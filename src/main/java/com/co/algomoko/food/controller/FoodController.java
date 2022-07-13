package com.co.algomoko.food.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.co.algomoko.food.domain.FoodVO;
import com.co.algomoko.food.mapper.FoodMapper;

@Controller
public class FoodController {
	@Autowired
	FoodMapper dao;

	@GetMapping("food")
	public String food(@RequestParam(value="ing", required=false) String ing, Model model) {
		if(ing != null) {
			// 검색어가 있으면 검색해서 띄우기
			FoodVO foodVO = new FoodVO();
			foodVO.setIng(ing);
			List<FoodVO> result = dao.fList(foodVO);
			model.addAttribute("fList", result);

			for (FoodVO r : result) {
				System.out.println(r.getIng());
			}
			// 인기검색어 구상
			// ing 검색수 +1
			// 조회수하듯이
			// 계란 0, 바나나 0, 물 3
			// order by 조회수 내림차순 불러오면 인기순임
		}
		// 초기페이지는 검색없이 바로 화면띄우기
		return "contents/food/food";
	}
  
	@GetMapping("foodContents")
	public String foodContents(@RequestParam(value="ing", required=false) String ing, Model model) {
		FoodVO foodVO = new FoodVO();
		foodVO.setIng(ing);
		FoodVO result = dao.fListOne(foodVO); 
		model.addAttribute("fListOne", result);
		
		if(ing != null) {
			// 검색어가 있으면 검색해서 띄우기
			FoodVO foodVO2 = new FoodVO();
			foodVO2.setIng(ing);
			List<FoodVO> result2 = dao.fList(foodVO2);
			result2.remove(0);
			for (FoodVO r : result2) {
				System.out.println(r.getIng());
			}
			model.addAttribute("fList", result2);
		}
		return "contents/food/foodContents";
	}
  
	@GetMapping("getSearchList")
	@ResponseBody
	public List<FoodVO>  getSearchList(Model model, FoodVO foodVO) {
		return dao.fList(foodVO);
	}
}
