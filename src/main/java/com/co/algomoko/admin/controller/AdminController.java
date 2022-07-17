package com.co.algomoko.admin.controller;

import com.co.algomoko.admin.domain.AdminVO;
import com.co.algomoko.admin.mapper.AdminMapper;
import com.co.algomoko.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AdminController {
    // 경로 이동
    @Autowired
    AdminService adminService;

    @Autowired
    AdminMapper adminMapper;

    @GetMapping("/Admin")
    public String moveAdminpage() throws Exception{
        return "contents/admin/userFormMain";
    }


    @GetMapping("/ReportUser")
    public String moveUserReport() throws Exception{
        return "contents/admin/userFormReportUser";
    }

    @GetMapping("/ReportUserDetail")
    public String moveUserReportDetail() throws Exception{
        return "contents/admin/userFormReportUserDatail";
    }

    @GetMapping("/Statistics")
    public String moveUserStatistics() throws Exception{
        return "contents/admin/userFormStatistics";
    }

    @GetMapping("/UserList")
    public String moveUserList(Model model) throws Exception{
        model.addAttribute("list", adminService.makeList());
        return "contents/admin/userFormUserlist";
    }
    @GetMapping("/moveInquiry")
    public String moveInquiry() throws Exception{
        return "contents/support/Inquiry";
    }

    @GetMapping("/moveFAQ")
    public String moveFAQ() throws Exception{
        return "contents/support/FAQ";
    }


    // 유저 리스트 - 상세 조회 및 제한 여부 창 이동
    @GetMapping("/userRestrict" )
    public String moveUserRestrict(@RequestParam("uid") String uid, Model model)  throws Exception{
        AdminVO adminVO = new AdminVO();
        adminVO.setUid(uid);
        model.addAttribute("detailList", adminMapper.findDetailUser(adminVO));
        return "contents/admin/userFormRestrict";
    }
}
