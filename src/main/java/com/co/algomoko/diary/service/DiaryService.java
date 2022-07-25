package com.co.algomoko.diary.service;

import com.co.algomoko.diary.domain.DiaryVO;
import com.co.algomoko.diary.domain.RecipeVO;
import com.co.algomoko.user.domain.UserVO;
import com.co.algomoko.user.mapper.UserMapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public interface DiaryService {
    public List<DiaryVO> findDay(DiaryVO diaryVO);
    public List<DiaryVO> sicDay(DiaryVO diaryVO);
    public List<DiaryVO> resultCal(DiaryVO diaryVO);
    public List<DiaryVO> detail(DiaryVO diaryVO);
    public List<DiaryVO> con(DiaryVO diaryVO);
    public List<DiaryVO> jukcal(DiaryVO diaryVO);
    public String insert(DiaryVO diaryVO); //등록
    public String insertdetail(DiaryVO diaryVO);
    public List<DiaryVO> custom(DiaryVO diaryVO);
    public List<RecipeVO> rerank(RecipeVO recpvo);
    public DiaryVO fonlist(String diaryVO);
    public DiaryVO diaryde(DiaryVO diaryVO);
	 public DiaryVO detade(DiaryVO diaryVO);
	 public List<RecipeVO> rlist(RecipeVO recpvo);
	 public List<RecipeVO> onelist(RecipeVO recpvo);
	 public List<RecipeVO> redetail(RecipeVO recpvo);
	 public int tcal(String mid);
}

