package com.co.algomoko.diary.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.co.algomoko.diary.domain.DiaryVO;

public interface DiaryMapper {
	public List<DiaryVO> findDay(DiaryVO diaryVO);
	public List<DiaryVO> sicDay(DiaryVO diaryVO);
	public List<DiaryVO> resultCal(DiaryVO diaryVO);
	public List<DiaryVO> jukcal(DiaryVO diaryVO);
	public List<DiaryVO> detail(DiaryVO diaryVO);
	 public List<DiaryVO> con(DiaryVO diaryVO);
	 public String insertdetail(DiaryVO diaryVO); // 상세 등록
	 public String insert(DiaryVO diaryVO); // 등록
	 public List<DiaryVO> custom(DiaryVO diaryVO);
}
