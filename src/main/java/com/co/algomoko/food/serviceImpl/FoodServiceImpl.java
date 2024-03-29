package com.co.algomoko.food.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.co.algomoko.food.domain.FoodVO;
import com.co.algomoko.food.domain.Page;
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

	@Override
	public List<Map<String, Object>> fListPage(FoodVO foodVO) throws Exception {
		return mapper.fListPage(foodVO);
	}

	@Override
	public int TableCount(FoodVO foodVO) throws Exception {
		return mapper.TableCount(foodVO);
	}

	@Override
	public int allTableCount(FoodVO foodVO) throws Exception {
		return mapper.allTableCount(foodVO);
	}

	@Override
	public void fInsert(FoodVO fVO) {
		
	}

	@Override
	public int fUpdate(FoodVO fVO) {
		return mapper.fUpdate(fVO);
	}

	@Override
	public void fDelete(String ing) {
		mapper.fDelete(ing);
	}

	


}
