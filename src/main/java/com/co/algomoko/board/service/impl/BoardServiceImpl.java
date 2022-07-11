package com.co.algomoko.board.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.algomoko.board.domain.BoardVO;
import com.co.algomoko.board.mapper.BoardMapper;
import com.co.algomoko.board.service.BoardService;

@Service
public class BoardServiceImpl implements BoardService{
	@Autowired BoardMapper mapper;
	
	
	@Override
	public List<BoardVO> getBoardList() {
		
		return mapper.getBoardList(null);
	}
}
