package com.co.algomoko;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.co.algomoko.board.mapper.BoardMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BTest {
	@Autowired BoardMapper mapper;
	
	@Test
	void list() {
		mapper.getBoardList();
	}
}
