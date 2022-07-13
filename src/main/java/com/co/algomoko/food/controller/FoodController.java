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
	
	@GetMapping("foodtest")
	public String foodtest() {
		return "contents/food/foodtest";
	}



	@GetMapping("food")
	public String food() {
		return "contents/food/food";
	}
  
	@GetMapping("foodContents")
	public String foodContents(@RequestParam(value="ing", required=false) String ing, Model model) {
		FoodVO foodVO = new FoodVO();
		foodVO.setIng(ing);
		FoodVO result = dao.fListOne(foodVO); 
		model.addAttribute("fListOne", result);
		return "contents/food/foodContents";
	}
  
	@GetMapping("getSearchList")
	@ResponseBody
	public List<FoodVO>  getSearchList(Model model, FoodVO foodVO) {
		return dao.fList(foodVO);
	}
}
