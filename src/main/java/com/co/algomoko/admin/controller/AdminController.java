package com.co.algomoko.admin.controller;

import com.co.algomoko.admin.domain.AdminVO;
import com.co.algomoko.admin.mapper.AdminMapper;
import com.co.algomoko.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AdminController {

    // 경로 이동
    @Autowired
    AdminService adminService;

    @Autowired
    AdminMapper adminMapper;

    @GetMapping("/Admin")
    public String moveAdminpage(){
        return "contents/admin/userFormMain";
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

    @GetMapping("/UserList")
    public String moveUserList(Model model){
        model.addAttribute("list", adminService.makeList());
        return "contents/admin/userFormUserlist";
    }
    @GetMapping("/moveInquiry")
    public String moveInquiry(){
        return "contents/support/Inquiry";
    }

    @GetMapping("/moveFAQ")
    public String moveFAQ(){
        return "contents/support/FAQ";
    }


    // 유저 리스트 - 상세 조회 및 제한 여부 창 이동
    @GetMapping("/userRestrict")
    @ResponseBody
    public String moveUserRestrict(@RequestParam("uid") String uid, Model model){
        AdminVO adminVO = new AdminVO();
        adminVO.setUid(uid);
        model.addAttribute("detailList", adminMapper.findDetailUser(adminVO));
        return "contents/admin/userFormRestrict";
    }

    // 유저 리스트 - 검색
    @GetMapping("/findUserList")
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
