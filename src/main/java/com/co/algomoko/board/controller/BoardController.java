package com.co.algomoko.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.co.algomoko.board.mapper.BoardMapper;

@Controller
public class BoardController {
	@Autowired BoardMapper dao;
	
	@RequestMapping("/boardList")
	public String boardList(Model model) {
		model.addAttribute("boardList",dao.getBoardList());
		return "contents/community/board";
	}
}
