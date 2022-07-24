package com.co.algomoko.support.controller;

import com.co.algomoko.support.domain.FaqVO;
import com.co.algomoko.support.domain.InquiryVO;
import com.co.algomoko.support.service.FaqService;
import com.co.algomoko.support.service.InquiryService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class SupportController {


    private FaqService faqService;
    private final InquiryService inquiryService;


    @Autowired
   public SupportController(FaqService faqService, InquiryService inquiryService){
       this.faqService = faqService;
       this.inquiryService = inquiryService;
   }

    @GetMapping("/Inquiry")
    public String moveInquiry(Model model){
        model.addAttribute("list", inquiryService.list());
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

    @GetMapping("/Inquiry/writeInquiry")
    public String moveWriteInquiry(Model model){
        model.addAttribute("searchNo",inquiryService.searchNo());
        return "contents/support/WriteInquiry";
    }


    // FAQ 분야별 목록
    @GetMapping("/FAQList")
    @ResponseBody
    public List<FaqVO> FAQList(FaqVO faqVO, Model model){
        return faqService.FAQList(faqVO);
    }


    @GetMapping("/Inquiry/insert")
    public String insertInquiry(@RequestParam("title") String title,
                              @RequestParam("content") String content,
                              Model model){
        inquiryService.insertInquiry(title, content);
        return "contents/admin/userFormMain";
    }
}
