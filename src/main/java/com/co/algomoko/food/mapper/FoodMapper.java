package com.co.algomoko.food.mapper;

import java.util.List;

import com.co.algomoko.food.domain.FoodVO;

public interface FoodMapper {
	public List<FoodVO> fList(FoodVO foodVO);
	public FoodVO fListOne(FoodVO foodVO);
}
