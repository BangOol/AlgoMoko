package com.co.algomoko.food.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.co.algomoko.food.domain.FoodVO;
import com.co.algomoko.food.mapper.FoodMapper;
import com.co.algomoko.food.service.FoodService;

public class FoodServiceImpl implements FoodService {
	@Autowired
	FoodMapper mapper;

	@Override
	public List<FoodVO> fList(FoodVO foodVO) {
		return mapper.fList(foodVO);
	}

	@Override
	public FoodVO fListOne() {
		return mapper.fListOne();
	}
}
