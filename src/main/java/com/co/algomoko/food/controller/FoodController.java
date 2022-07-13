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
import com.co.algomoko.food.service.FoodService;
import com.co.algomoko.food.serviceImpl.FoodServiceImpl;

@Controller
public class FoodController {
	@Autowired
	FoodMapper dao;

	@GetMapping("food")
	public String food() {
		return "contents/food/food";
	}

	@GetMapping("foodContents")
	public String foodContents(@RequestParam(value="ing", required = true) String ing, Model model) {
		model.addAttribute("foodOne");
		
	}		
	@GetMapping("getSearchList")
	@ResponseBody
	public List<FoodVO>  getSearchList(Model model, FoodVO foodVO) {
		return dao.fList(foodVO);

	}
}