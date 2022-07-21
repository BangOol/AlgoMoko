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
    @Test
    // 유저리스트 창 이동(블랙리스트, 제한리스트 포함)
    @GetMapping("/UserList")
    public ModelAndView moveUserListTest(@RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
                                     @RequestParam(value = "cntPerPage", defaultValue = "10") int cntPerPage,
                                     @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                     Map<String, Object> map,
                                     Model model) throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        int listCnt = pagingService.TableCount();
        currentPage = 1;
        cntPerPage = 10;
        pageSize = 10;

        PaginationUser paginationUser = new PaginationUser(currentPage, cntPerPage, pageSize);
        paginationUser.setTotalRecordCount(listCnt);
        System.out.println(paginationUser);
        modelAndView.addObject("pagination", paginationUser);
        modelAndView.addObject("Alllist", pagingService.SelectAllList(paginationUser));
        modelAndView.setViewName("contents/admin/userFormUserlist");
//        model.addAttribute("list", adminService.makeList());
        System.out.println(modelAndView);
        return modelAndView;
    }
}
