package com.co.algomoko.support.controller;

import com.co.algomoko.support.domain.FaqVO;
import com.co.algomoko.support.mapper.FaqMapper;
import com.co.algomoko.support.mapper.InquiryMapper;
import com.co.algomoko.support.mapper.NoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class SupportController {

    @Autowired
    FaqMapper faqMapper;
    @Autowired
    NoticeMapper noticeMapper;
    @Autowired
    InquiryMapper inquiryMapper;

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

    @GetMapping("/writeInquiry")
    public String moveWriteInquiry(){
        return "contents/support/WriteInquiry";
    }


    // FAQ 분야별 목록
    @GetMapping("/FAQList")
    @ResponseBody
    public List<FaqVO> FAQList(FaqVO faqVO, Model model){
        return faqMapper.FAQList(faqVO);
    }

}
