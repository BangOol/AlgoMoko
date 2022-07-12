package com.co.algomoko.admin.controller;

import com.co.algomoko.admin.domain.AdminVO;
import com.co.algomoko.admin.mapper.AdminMapper;
import com.co.algomoko.admin.service.AdminService;
import com.co.algomoko.user.domain.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class AdminController {

    // 경로 이동
    @Autowired
    AdminService adminService;

    @GetMapping("/Admin")
    public String moveAdminpage(){
        return "contents/admin/userFormMain";
    }

    @GetMapping("/UserList")
    public String moveUserList(Model model){
        model.addAttribute("list", adminService.makeList());
        return "contents/admin/userFormUserlist";
    }
    @GetMapping("/ReportUser")
    public String moveUserReport(){
        return "contents/admin/userFormReportUser";
    }

    @GetMapping("/ReportUserDetail")
    public String moveUserReportDetail(){
        return "contents/admin/userFormReportUserDatail";
    }

    @GetMapping("/Statistics")
    public String moveUserStatistics(){
        return "contents/admin/userFormStatistics";
    }


    // 유저 리스트 - 검색
    @RequestMapping("/findUser")
    public String findUser(@RequestParam("type") String type, @RequestParam("keyword") String keyword, Model model) throws  Exception{
        UserVO userVO = new UserVO();


        return null;
    }



}
