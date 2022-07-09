package com.co.algomoko.user.controller;

import com.co.algomoko.user.domain.AlarmVO;
import com.co.algomoko.user.domain.UserVO;
import com.co.algomoko.user.service.UserService;
import com.co.algomoko.user.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    UserServiceImpl userService;

    @GetMapping("/main")
    public String TestControl(){
        return "contents/index.html";
    }
}
