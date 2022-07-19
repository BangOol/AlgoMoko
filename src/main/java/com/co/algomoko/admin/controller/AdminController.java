package com.co.algomoko.admin.controller;

import com.co.algomoko.admin.domain.AdminVO;
import com.co.algomoko.admin.domain.StatisticVO;
import com.co.algomoko.admin.mapper.AdminMapper;
import com.co.algomoko.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class AdminController {

    @Autowired
    AdminMapper adminMapper;

    // 관리자 메인 페이지 이동
    @GetMapping("/Admin")
    public String moveAdminpage() throws Exception{
        return "contents/admin/userFormMain";
    }



    // 신고 유저 상세창 이동
    @GetMapping("/ReportUserDetail")
    public String moveUserReportDetail() throws Exception{
        return "contents/admin/userFormReportUserDatail";
    }

    // 통계 메인 창 이동
    @GetMapping("/Statistics")
    public String mainUserStatistics(Model model) throws Exception{
        return "contents/admin/userFormStatistics";
    }
    // 통계 유저 통계 창 이동
    @GetMapping("/UserStatistics")
    public String moveUserStatistics(Model model) throws Exception{
        model.addAttribute("genderRate", adminMapper.genderRate()); // 성별 비율
        model.addAttribute("RestrictedRate", adminMapper.RestrictedRate()); // 현재 제한 비율
        model.addAttribute("heightRate", adminMapper.heightRate()); // 회원 별 키 비율
        model.addAttribute("ageRate", adminMapper.ageRate()); // 회원 나이 비율
        model.addAttribute("bmiRate", adminMapper.bmiRate()); // 회원 BMI 비율
        return "contents/admin/UserStatistics";
    }


    // 관리자 - 1:1 문의 창 이동
    @GetMapping("/moveInquiry")
    public String moveInquiry() throws Exception{
        return "contents/support/Inquiry";
    }

    // 관리자 - FAQ 창 이동
    @GetMapping("/moveFAQ")
    public String moveFAQ() throws Exception{
        return "contents/support/FAQ";
    }


    // 유저 리스트 - 상세 조회 및 제한 여부 창 이동
    @GetMapping("/userRestrict")
    public String moveUserRestrict(@RequestParam("uid") String uid, Model model)  throws Exception{
        AdminVO adminVO = new AdminVO();
        adminVO.setUid(uid);
        model.addAttribute("detailList", adminMapper.findDetailUser(adminVO));
        return "contents/admin/userFormRestrict";
    }

    // 유저 리스트 - 유저 제한상태 변경([ex]정상->3일 제한)
    @PostMapping("/insertRestrict")
    public String alertUserRestrict(@RequestParam("type") String type, @RequestParam("uid") String uid, Model model){
        AdminVO adminVO = new AdminVO();
        adminVO.setType(type);
        adminVO.setUid(uid);
        adminMapper.insertRestrict(adminVO);
        return "contents/admin/userFormUserlist";
    }
}
