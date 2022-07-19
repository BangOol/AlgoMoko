package com.co.algomoko.admin.controller;

import com.co.algomoko.admin.mapper.AdminMapper;
import com.co.algomoko.admin.paging.PaginationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@RestController
public class PagingController {

    @Autowired
    AdminMapper adminMapper;
    @GetMapping("/UserList")
    public ModelAndView moveUserList(@RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
                                     @RequestParam(value = "cntPerPage", defaultValue = "10") int cntPerPage,
                                     @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                     Map<String, Object> map,
                                     Model model) throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        int listCnt = adminMapper.TableCount();
        PaginationUser paginationUser = new PaginationUser(currentPage, cntPerPage, pageSize);
        paginationUser.setTotalRecordCount(listCnt);
        System.out.println(paginationUser);

        modelAndView.addObject("pagination", paginationUser);
        modelAndView.addObject("Alllist", adminMapper.SelectAllList(paginationUser));
        modelAndView.setViewName("contents/admin/userFormUserlist");
//        model.addAttribute("list", adminService.makeList());
        System.out.println(modelAndView);
        return modelAndView;
    }

}
