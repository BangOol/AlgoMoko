package com.co.algomoko.admin.controller;

import com.co.algomoko.admin.domain.AdminVO;
import com.co.algomoko.admin.service.AdminService;
import com.co.algomoko.admin.service.PagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminRestController {

    /*
       생성자로 AdminService를 처음 한 번만 설정할 수 있다.
       그 이후 AdminService는 final로 선언되어 있기 때문에 외부로부터 수정이 불가능하다.
       즉, 캡슐화로 외부로부터 접근과 수정이 불가능하다.

       AdminService는 스프링을 통해 싱글톤 패턴으로 관리되기 때문에
       외부로부터 접근을 막는 것이 필수이며, 아래의 코드가 가장 이상적이다.
     */

    private AdminService adminService;
    private PagingService pagingService;
    @Autowired
    public AdminRestController(AdminService adminService){
        this.adminService = adminService;
    }

    // 유저 리스트 - 검색
    @GetMapping("/findUserList")
    public List<AdminVO> findUserList(@RequestParam("type") String type, @RequestParam("keyword") String keyword, Model model) throws  Exception{
        AdminVO adminVO = new AdminVO();
        adminVO.setType(type);
        adminVO.setKeyword(keyword);
        return adminService.findUserList(adminVO);
    }
//    // 블랙리스트 조회
//    @PostMapping("/findBlackList")
//    public List<AdminVO> findBlackList(){
//        return  adminService.findBlackList();
//    }
//    // 제한 조치 유저 조회
//    @PostMapping("/findRestrictList")
//    public List<AdminVO> findRestrictList(){
//        return adminService.findRestrictList();
//    }
//    // 전체 유저 조회
//    @PostMapping("/findAllList")
//    public List<AdminVO> findAllList() {
//        return .();
//    }

    // 유저리스트 창 이동(블랙리스트, 제한리스트 포함)
//    @GetMapping("/UserList")
//    public String moveUserList(Model model) throws Exception{
//        model.addAttribute("list", adminService.makeList());
//        return "contents/admin/userFormUserlist";
//    }
    // 유저리스트 창 이동(블랙리스트, 제한리스트 포함)

}
