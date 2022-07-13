package com.co.algomoko.food.service;

import java.util.List;

import com.co.algomoko.food.domain.FoodVO;

public interface FoodService {
	public List<FoodVO> fList(FoodVO foodVO);
	public FoodVO fListOne(FoodVO foodVO);
}
