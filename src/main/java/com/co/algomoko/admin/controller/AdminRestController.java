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
    @GetMapping("/findUserList")
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
    // 블랙리스트 조회
    @PostMapping("/findBlackList")
    public List<AdminVO> findBlackList(){
        return  adminMapper.findBlackList();
    }
    // 제한 조치 유저 조회
    @PostMapping("/findRestrictList")
    public List<AdminVO> findRestrictList(){
        return adminMapper.findRestrictList();
    }
    // 전체 유저 조회
    @PostMapping("/findAllList")
    public List<AdminVO> findAllList() {
        return adminMapper.makeList();
    }

    // 유저리스트 창 이동(블랙리스트, 제한리스트 포함)
//    @GetMapping("/UserList")
//    public String moveUserList(Model model) throws Exception{
//        model.addAttribute("list", adminService.makeList());
//        return "contents/admin/userFormUserlist";
//    }
    // 유저리스트 창 이동(블랙리스트, 제한리스트 포함)

}
