package com.co.algomoko.board.mapper;

import java.util.List;

import com.co.algomoko.board.domain.BoardVO;

public interface BoardMapper {
	public List<BoardVO> getBoardList(BoardVO boardvo);
}
