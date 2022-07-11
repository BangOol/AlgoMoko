package com.co.algomoko.diary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.co.algomoko.diary.domain.DiaryVO;
import com.co.algomoko.diary.mapper.DiaryMapper;

public class DiaryServiceImpl implements DiaryService {

	
	@Autowired DiaryMapper diaryMapper;

    	@Override
	public List<DiaryVO> findDay() {
		return diaryMapper.findDay();
	}

}
