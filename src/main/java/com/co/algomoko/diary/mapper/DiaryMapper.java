package com.co.algomoko.diary.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.co.algomoko.diary.domain.DiaryVO;
import com.co.algomoko.diary.domain.DiaryVO2;
import com.co.algomoko.diary.domain.RecipeVO;

public interface DiaryMapper {
	public List<DiaryVO> findDay(DiaryVO diaryVO);
	public List<DiaryVO> sicDay(DiaryVO diaryVO);
	public List<DiaryVO> resultCal(DiaryVO diaryVO);
	public List<DiaryVO2> resultCal1(DiaryVO2 diaryVO2);
	public List<DiaryVO> jukcal(DiaryVO diaryVO);
	public List<DiaryVO> detail(DiaryVO diaryVO);
	 public List<DiaryVO> con(DiaryVO diaryVO);
	 public String insertdetail(DiaryVO diaryVO); // 상세 등록
	 public String insert(DiaryVO diaryVO); // 등록
	 public List<DiaryVO> custom(DiaryVO diaryVO);
	 public List<RecipeVO> rerank(RecipeVO recpvo);
	 public DiaryVO fonlist(String ddnames);
	 public DiaryVO diaryde(DiaryVO diaryVO);
	 public DiaryVO detade(DiaryVO diaryVO);
	 public List<RecipeVO> rlist(RecipeVO recpvo);
	 public List<RecipeVO> onelist(RecipeVO recpvo);
	 public List<RecipeVO> redetail(RecipeVO recpvo);
	 public int tcal(String mid);
	 public String tomem(String mid);
	 public void reinsert(RecipeVO recipeVO);
	 public void redeinsert(RecipeVO recipeVO);
	 public void redelete(RecipeVO recipeVO);
	 public void rededelete(RecipeVO recipeVO);
	
}
