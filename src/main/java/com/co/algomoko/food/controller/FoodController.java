package com.co.algomoko.food.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FoodController {
	
	@GetMapping("food")
	public String food() {
		return "contents/food/food";
	}
	@GetMapping("foodContents")
	public String foodContents() {
		return "contents/food/foodContents";
	}
}
