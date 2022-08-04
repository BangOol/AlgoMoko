package com.co.algomoko.food.service;

import java.util.List;
import java.util.Map;

import com.co.algomoko.challenge.domain.ChallengeVO;
import com.co.algomoko.food.domain.FoodVO;
import com.co.algomoko.food.domain.Page;

public interface FoodService {
	// 음식 검색 리스트 조회
	public List<FoodVO> fList(FoodVO foodVO);

	// 음식 검색 단건 조회
	public FoodVO fListOne(FoodVO foodVO);

	// 음식 조회수 증가
	public int plusView(String ing);

	// 인기 검색어 리스트 6등까지
	public List<FoodVO> pList(FoodVO foodVO);

	// 연관 검색어
	public List<FoodVO> rList(FoodVO foodVO);

	// 페이징
	public List<Map<String, Object>> fListPage(FoodVO foodVO) throws Exception;

	// count
	public int TableCount(FoodVO foodVO) throws Exception;

	// allcount
	public int allTableCount(FoodVO foodVO) throws Exception;

	// 음식 작성
	public void fInsert(FoodVO fVO);

	// 챌린지 수정(관리자만)
	public int fUpdate(FoodVO fVO);

	// 챌린지 삭제(관리자만)
	public void fDelete(String ing);

}
