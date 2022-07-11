package com.co.algomoko.diary.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.co.algomoko.diary.domain.DiaryVO;
import com.co.algomoko.diary.mapper.DiaryMapper;
import com.co.algomoko.diary.service.DiaryService;

public class DiaryServiceImpl implements DiaryService {

	
	@Autowired DiaryMapper diaryMapper;

    @Override
	public List<DiaryVO> findDay() {
    	
		return diaryMapper.findDay();
	}

}
