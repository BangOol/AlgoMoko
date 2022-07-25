package com.co.algomoko.diary.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.co.algomoko.diary.domain.DiaryVO;
import com.co.algomoko.diary.domain.RecipeVO;
import com.co.algomoko.diary.mapper.DiaryMapper;
import com.co.algomoko.diary.service.DiaryService;

public class DiaryServiceImpl implements DiaryService {

	
	@Autowired DiaryMapper diaryMapper;

    @Override
	public List<DiaryVO> findDay(DiaryVO diaryVO) {
    	
		return diaryMapper.findDay(diaryVO);
	}

	@Override
	public List<DiaryVO> sicDay(DiaryVO diaryVO) {

		
		return diaryMapper.sicDay(diaryVO);
	}

	@Override
	public List<DiaryVO> resultCal(DiaryVO diaryVO) {
		return diaryMapper.resultCal(diaryVO);

	}
	@Override
	public List<DiaryVO> detail(DiaryVO diaryVO) {
		return diaryMapper.detail(diaryVO);

	}

	@Override
	public List<DiaryVO> con(DiaryVO diaryVO) {
		
		return diaryMapper.con(diaryVO);
	}

	@Override
	public List<DiaryVO> jukcal(DiaryVO diaryVO) {
		
		return diaryMapper.jukcal(diaryVO);
	}

	@Override
	public String insert(DiaryVO diaryVO) {
		return diaryMapper.insert(diaryVO);
		
		
	}

	@Override
	public String insertdetail(DiaryVO diaryVO) {
		return diaryMapper.insertdetail(diaryVO);
	}

	@Override
	public List<DiaryVO> custom(DiaryVO diaryVO) {
		return diaryMapper.custom(diaryVO);
	}

	@Override
	public List<RecipeVO> rerank(RecipeVO recpvo) {
		return diaryMapper.rerank(recpvo);
	}

	@Override
	public DiaryVO fonlist(String diaryVO) {
		return diaryMapper.fonlist(diaryVO);
	}

	@Override
	public DiaryVO diaryde(DiaryVO diaryVO) {
		return diaryMapper.diaryde(diaryVO);
	}
	

	@Override
	public DiaryVO detade(DiaryVO diaryVO) {
		return diaryMapper.detade(diaryVO);
	}

	@Override
	public List<RecipeVO> rlist(RecipeVO recpvo) {
		return diaryMapper.rlist(recpvo);
	}

	@Override
	public List<RecipeVO> redetail(RecipeVO recpvo) {
		return diaryMapper.redetail(recpvo);
	}

	@Override
	public List<RecipeVO> onelist(RecipeVO recpvo) {
		return diaryMapper.onelist(recpvo);
	}

	@Override
	public int tcal(String mid) {
		
		return diaryMapper.tcal(mid);
	}

	

	

}
