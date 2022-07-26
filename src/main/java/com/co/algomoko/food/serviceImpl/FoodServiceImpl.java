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
	public FoodVO fListOne(FoodVO foodVO) {
		return null;
	}

	@Override
	public int plusView(String ing) {
		return mapper.plusView(ing);
	}

	@Override
	public List<FoodVO> pList(FoodVO foodVO) {
		return mapper.pList(foodVO);
	}

	@Override
	public List<FoodVO> rList(FoodVO foodVO) {
		return mapper.rList(foodVO);
	}

	


}
