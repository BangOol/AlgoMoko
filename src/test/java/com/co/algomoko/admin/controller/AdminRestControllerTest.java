package com.co.algomoko.admin.controller;

import com.co.algomoko.admin.mapper.AdminMapper;
import com.co.algomoko.admin.paging.PaginationUser;
import com.co.algomoko.admin.service.PagingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@SpringBootTest
class AdminRestControllerTest {

    @Autowired
    AdminMapper adminMapper;
    @Autowired
    PagingService pagingService;

}
