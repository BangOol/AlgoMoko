package com.co.algomoko.support.controller;

import com.co.algomoko.admin.paging.PaginationUser;
import com.co.algomoko.admin.service.PagingService;
import com.co.algomoko.support.domain.FaqVO;
import com.co.algomoko.support.domain.InquiryVO;
import com.co.algomoko.support.service.FaqService;
import com.co.algomoko.support.service.InqPagingService;
import com.co.algomoko.support.service.InquiryService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class SupportController {


    private FaqService faqService;
    private final InquiryService inquiryService;
    private final InqPagingService pagingService;

    @Autowired
   public SupportController(FaqService faqService, InquiryService inquiryService, InqPagingService pagingService){
       this.faqService = faqService;
       this.inquiryService = inquiryService;
       this.pagingService = pagingService;
   }



    // 관리자 전용 - FAQ 창 이동
    @GetMapping("FAQ")
    public String moveFAQ() throws Exception{
        return "contents/support/FAQ";
    }


    @GetMapping("/Inquiry")
    public ModelAndView moveInquiry(@RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
                                    @RequestParam(value = "cntPerPage", defaultValue = "10") int cntPerPage,
                                    @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                    @RequestParam(value = "type", defaultValue = "null") String type,
                                    @RequestParam(value = "keyword", defaultValue = "null") String keyword) throws Exception {

        ModelAndView modelAndView = new ModelAndView();
        // 전체 회원 수
        int listCnt = pagingService.inqTableCount(); // 전체 테이블 계산
        PaginationUser paginationUser = new PaginationUser(currentPage, cntPerPage, pageSize);
        paginationUser.setType(type);
        paginationUser.setKeyword(keyword);
        paginationUser.setTotalRecordCount(listCnt);

        modelAndView.addObject("pagination",paginationUser);
        modelAndView.addObject("InqList", pagingService.inqAllList(paginationUser));
        modelAndView.setViewName("contents/support/Inquiry");

        return modelAndView;
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
