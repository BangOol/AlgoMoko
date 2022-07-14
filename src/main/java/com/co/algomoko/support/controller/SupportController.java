package com.co.algomoko.support.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SupportController {
    @GetMapping("/Inquiry")
    public String moveInquiry(){
        return "contents/support/Inquiry";
    }

    @GetMapping("/FAQ")
    public String moveFAQ(){
        return "contents/support/FAQ";
    }
    @GetMapping("/Notice")
    public String moveNotice(){
        return "contents/support/Notice";
    }
}
