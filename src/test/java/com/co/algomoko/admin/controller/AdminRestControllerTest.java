package com.co.algomoko.admin.controller;

import com.co.algomoko.admin.domain.AdminVO;
import com.co.algomoko.admin.mapper.AdminMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class AdminRestControllerTest {

    @Autowired
    AdminMapper adminMapper;
    @Test
    public void findBlackListTest(){
        List<AdminVO> exam = adminMapper.findBlackList();
        System.out.println(adminMapper.findBlackList());

    }
}