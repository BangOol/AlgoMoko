package com.co.algomoko.support.controller;

import com.co.algomoko.admin.domain.AdminVO;
import com.co.algomoko.admin.paging.PaginationUser;
import com.co.algomoko.support.domain.FaqVO;
import com.co.algomoko.support.domain.InquiryVO;
import com.co.algomoko.support.service.FaqService;
import com.co.algomoko.support.service.InqPagingService;
import com.co.algomoko.support.service.InquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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


    // 1:1 문의 이동
    @GetMapping("/Inquiry")
    public ModelAndView moveInquiry(@RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
                                    @RequestParam(value = "cntPerPage", defaultValue = "10") int cntPerPage,
                                    @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                    @RequestParam(value = "type", defaultValue = "null") String type,
                                    @RequestParam(value = "keyword", defaultValue = "null") String keyword,
                                    Authentication authentication) throws Exception {

        ModelAndView modelAndView = new ModelAndView();
        
        // 회원 아이디 받기
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String mid = userDetails.getUsername();
        String u0 = inquiryService.getU0(mid);

        // 전체 회원 수
        int listCnt = pagingService.inqTableCount(); // 전체 테이블 계산
        PaginationUser paginationUser = new PaginationUser(currentPage, cntPerPage, pageSize);
        paginationUser.setType(type);
        paginationUser.setKeyword(keyword);
        paginationUser.setTotalRecordCount(listCnt);

        modelAndView.addObject("pagination",paginationUser);
        modelAndView.addObject("InqList", pagingService.inqAllList(paginationUser));
        modelAndView.addObject("U0", u0);
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
                              @RequestParam("qno") int qno,
                              @RequestParam("option") String C0,
                              Model model,
                              Authentication authentication,
                              HttpServletResponse response) throws IOException {
        // 유저의 아이디 값 호출
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String mid = userDetails.getUsername();
        // user nickname 호출
        String nick = inquiryService.getNick(mid);
        String a0 = "a0";
        String qans = "확인 중";

        InquiryVO inquiryVO = new InquiryVO();
        inquiryVO.setQtitle(title);
        inquiryVO.setQcon(content);
        inquiryVO.setQno(qno);
        inquiryVO.setNick(nick);
        inquiryVO.setMid(mid);
        inquiryVO.setQans(qans);
        inquiryVO.setA0(a0);
        inquiryVO.setC0(C0);

        inquiryService.insertInquiry(inquiryVO);

        String msg = "정상적으로 등록되었습니다";
        String url = "/Inquiry";
        response.setContentType("text/html; charset=utf-8");
        PrintWriter w = response.getWriter();
        w.write("<script>alert('"+msg+"');location.href='"+url+"';</script>");

        return "contents/admin/userFormMain";
    }

    @GetMapping("/Inquiry/detail")
    public String InquiryDetail(@RequestParam("nick") String nick,@RequestParam("qtitle") String qtitle,
                                 Authentication authentication,Model model){
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        String username = userDetails.getUsername(); // user 아이디 확인
        String u0 = inquiryService.getU0(username);// 아이디를 이용하여 해당 사용자가 관리자인지 확인.

        InquiryVO inquiryVO = new InquiryVO();
        inquiryVO.setNick(nick);
        inquiryVO.setQtitle(qtitle);

        model.addAttribute("list",inquiryService.InquiryDetail(inquiryVO));
        model.addAttribute("u0", u0);
        return "contents/support/detail";
    }

    @GetMapping("/Inquiry/inqResult")
    public void inqResult(@RequestParam("title") String title,
                          @RequestParam("content") String content,
                          @RequestParam("nick") String nick,
                          @RequestParam("a0") String a0,
                          Model model,
                          Authentication authentication) throws  Exception{
        InquiryVO inquiryVO = new InquiryVO();
        inquiryVO.setQans(content);
        inquiryVO.setNick(nick);


    }
}
