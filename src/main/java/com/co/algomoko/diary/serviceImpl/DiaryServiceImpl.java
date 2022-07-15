package com.co.algomoko.diary.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.co.algomoko.diary.domain.DiaryVO;
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

}
