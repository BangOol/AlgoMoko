package com.co.algomoko.admin.controller;

import com.co.algomoko.admin.domain.AdminVO;
import com.co.algomoko.admin.mapper.AdminMapper;
import com.co.algomoko.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminRestController {

    // 경로 이동
    @Autowired
    AdminService adminService;

    @Autowired
    AdminMapper adminMapper;


    // 유저 리스트 - 검색
    @PostMapping("/findUserList")
    @ResponseBody
    public List<AdminVO> findUserList(@RequestParam("type") String type, @RequestParam("keyword") String keyword, Model model) throws  Exception{
        AdminVO adminVO = new AdminVO();
        adminVO.setType(type);
        adminVO.setKeyword(keyword);
        if(keyword == ""){
            return adminMapper.makeList();
        } else {
            return adminMapper.findUserList(adminVO);
        }
    }



}
