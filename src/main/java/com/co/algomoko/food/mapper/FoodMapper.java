package com.co.algomoko.food.mapper;

import java.util.List;

import com.co.algomoko.food.vo.FoodVO;

public interface FoodMapper {
	// 음식 검색 결과 리스트
	public List<FoodVO> fList();
	// 음식 검색 결과 하나 조회
	public FoodVO fListOne();
}
