package com.co.algomoko.diary.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.co.algomoko.diary.domain.DiaryVO;

public interface DiaryMapper {
	public List<DiaryVO> findDay(DiaryVO diaryVO);
	public List<DiaryVO> sicDay(DiaryVO diaryVO);
	
	
}
