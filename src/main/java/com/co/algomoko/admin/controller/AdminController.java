package com.co.algomoko.admin.controller;

import com.co.algomoko.admin.domain.AdminVO;
import com.co.algomoko.admin.paging.PaginationUser;
import com.co.algomoko.admin.service.AdminService;
import com.co.algomoko.admin.service.PagingService;
import com.co.algomoko.user.domain.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/Admin")
public class AdminController {

    /*
       생성자로 AdminService를 처음 한 번만 설정할 수 있다.
       그 이후 AdminService는 final로 선언되어 있기 때문에 외부로부터 수정이 불가능하다.
       즉, 캡슐화로 외부로부터 접근과 수정이 불가능하다.

       AdminService는 스프링을 통해 싱글톤 패턴으로 관리되기 때문에
       외부로부터 접근을 막는 것이 필수이며, 아래의 코드가 가장 이상적이다.
     */

    private AdminService adminService;
    private final PagingService pagingService;
    @Autowired
    public AdminController(AdminService adminService, PagingService pagingService){
        this.adminService = adminService;
        this.pagingService = pagingService;
    }

    // 관리자 메인 페이지 이동
    @GetMapping("")
    public String moveAdminpage() throws Exception{
        return "contents/admin/userFormMain";
    }

    @GetMapping("UserList")
    public ModelAndView moveUserList(@RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
                               @RequestParam(value = "cntPerPage", defaultValue = "10") int cntPerPage,
                               @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                               @RequestParam(value = "type", defaultValue = "null") String type,
                               @RequestParam(value = "keyword", defaultValue = "null") String keyword) throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        // 전체 회원 수
        int listCnt = pagingService.TableCount();
        //view 단에서 받은 현재 페이지, 페이지 당 출력 페이지 개수, 화면 하단 페이지 사이즈 가져와서 입력.
        PaginationUser paginationUser = new PaginationUser(currentPage, cntPerPage, pageSize, type, keyword);

        /*
        전체 회원 수를 입력하여 0보다 컸을 때
        전체 페이지 수, 리스트의 첫 페이지 번호, 마지막 번호, ROW_NUM의 첫, 마지막 값,
        이전 페이지 존재 여부, 다음 페이지 존재 여부를 확인하고, paginationUser에 넣는다.
        */
        paginationUser.setTotalRecordCount(listCnt);
        modelAndView.addObject("pagination", paginationUser); // 값을 paginantion으로 뿌림.
        modelAndView.addObject("Alllist", pagingService.SelectAllList(paginationUser)); // 회원 전체 데이터를 뿌림.
        modelAndView.setViewName("contents/admin/userFormUserlist");
        System.out.println(modelAndView);
        return modelAndView;
    }

    // 신고 유저 상세창 이동
    @GetMapping("ReportUserDetail")
    public String moveUserReportDetail() throws Exception{
        return "contents/admin/userFormReportUserDatail";
    }

    // 통계 메인 창 이동
    @GetMapping("Statistics")
    public String mainUserStatistics(Model model) throws Exception{
        return "contents/admin/userFormStatistics";
    }
    // 통계 유저 통계 창 이동
    @GetMapping("UserStatistics")
    public String moveUserStatistics(Model model) throws Exception{
        model.addAttribute("genderRate", adminService.genderRate()); // 성별 비율
        model.addAttribute("RestrictedRate", adminService.RestrictedRate()); // 현재 제한 비율
        model.addAttribute("heightRate", adminService.heightRate()); // 회원 별 키 비율
        model.addAttribute("ageRate", adminService.ageRate()); // 회원 나이 비율
        model.addAttribute("bmiRate", adminService.bmiRate()); // 회원 BMI 비율
        return "contents/admin/UserStatistics";
    }


    // 관리자 전용 - 1:1 문의 창 이동
    @GetMapping("Inquiry")
    public String moveInquiry() throws Exception{
        return "contents/support/Inquiry";
    }

    // 관리자 전용 - FAQ 창 이동
    @GetMapping("FAQ")
    public String moveFAQ() throws Exception{
        return "contents/support/FAQ";
    }


    // 유저 리스트 - 상세 조회 및 제한 여부 창 이동
    @GetMapping("userRestrict")
    public String moveUserRestrict(@RequestParam("uid") String uid, Model model)  throws Exception{
        AdminVO adminVO = new AdminVO();
        adminVO.setUid(uid);
        model.addAttribute("detailList", adminService.findDetailUser(adminVO));
        return "contents/admin/userFormRestrict";
    }

    // 유저 리스트 - 유저 제한상태 변경([ex]정상->3일 제한)
    @PostMapping("insertRestrict")
    public String alertUserRestrict(@RequestParam("type") String type, @RequestParam("uid") String uid, Model model){
        AdminVO adminVO = new AdminVO();
        adminVO.setType(type);
        adminVO.setUid(uid);
        adminService.insertRestrict(adminVO);
        return "contents/admin/userFormUserlist";
    }
}
