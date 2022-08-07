package com.co.algomoko.support.controller;

import com.co.algomoko.admin.paging.PaginationUser;
import com.co.algomoko.support.domain.FaqVO;
import com.co.algomoko.support.domain.InquiryVO;
import com.co.algomoko.support.service.FaqService;
import com.co.algomoko.support.service.InqPagingService;
import com.co.algomoko.support.service.InquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.UserDataHandler;

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
    public String moveWriteInquiry(Authentication authentication,Model model){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String mid = userDetails.getUsername();
        model.addAttribute("searchNo",inquiryService.searchNo());
        model.addAttribute("mid",mid);
        return "contents/support/WriteInquiry";
    }


    // FAQ 분야별 목록
    @GetMapping("/FAQList")
    @ResponseBody
    public List<FaqVO> FAQList(FaqVO faqVO, Model model){
        return faqService.FAQList(faqVO);
    }


    @PostMapping("/Inquiry/insert")
    public void insertInquiry(InquiryVO inquiryVO,
                              Model model, HttpServletResponse response,
                              Authentication authentication) throws IOException {

        // 유저의 아이디 값 호출
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String mid = userDetails.getUsername();
        // user nickname 호출
        String nick = inquiryService.getNick(mid);
        inquiryVO.setMid(mid);
        inquiryVO.setNick(nick);
        System.out.println(inquiryVO);
        inquiryService.insertInquiry(inquiryVO);


        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter w = response.getWriter();
        String msg = "정상적으로 등록되었습니다";
        String url = "/Inquiry";
        w.write("<script>alert('"+msg+"');location.href='"+url+"';</script>");

    }

    @PostMapping("/Inquiry/modify")
    public String modifyInquiry(@RequestParam("nick") String nick,
                                @RequestParam("qno") int qno,
                                @RequestParam("qcon") String qcon,
                                @RequestParam("qtitle") String qtitle,
                                Model model, Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        InquiryVO inquiryVO = new InquiryVO();
        inquiryVO.setQno(qno);
        inquiryVO.setNick(nick);
        inquiryVO.setQtitle(qtitle);
        inquiryVO.setQcon(qcon);
        model.addAttribute("list", inquiryVO);
        return "contents/support/insertInquiry";
    }

    @PostMapping("/Inquiry/updateInq")
    public void updateInq(@RequestParam("qno") int qno,
                            @RequestParam("qcon") String qcon,
                            @RequestParam("qtitle") String qtitle,
                            @RequestParam("nick") String nick,
                            @RequestParam("option") String c0,
                            Model model, HttpServletResponse response,
                            Authentication authentication) throws IOException {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String mid = userDetails.getUsername();
        InquiryVO inquiryVO = new InquiryVO();
        inquiryVO.setQcon(qcon);
        inquiryVO.setQno(qno);
        inquiryVO.setQtitle(qtitle);
        inquiryVO.setNick(nick);
        inquiryVO.setC0(c0);

        int a = inquiryService.updateInquiry(inquiryVO);
        if(a != 0){
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=utf-8");
            PrintWriter w = response.getWriter();
            String msg = "정상적으로 등록되었습니다";
            String url = "/Inquiry";
            w.write("<script>alert('"+msg+"');location.href='"+url+"';</script>");
        } else{
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=utf-8");
            PrintWriter w = response.getWriter();
            String msg = "예기지 않은 문제로 인해 등록이 실패하였습니다.";
            String url = "/Inquiry";
            w.write("<script>alert('"+msg+"');location.href='"+url+"';</script>");
        }
    }

    @PostMapping("/Inquiry/delete")
    public void InquiryDelete(@RequestParam("qno") int qno,
                              @RequestParam("mid") String mid,
                              @RequestParam("nick") String nick,
                              Authentication authentication,
                              HttpServletResponse response) throws IOException {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        if(userDetails.getUsername().equals(mid)){
            InquiryVO inquiryVO = new InquiryVO();
            inquiryVO.setQno(qno);
            inquiryVO.setNick(nick);
            inquiryVO.setMid(mid);
            int a = inquiryService.deleteInquiry(inquiryVO);

            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=utf-8");
            PrintWriter w = response.getWriter();
            String msg;
            String url = "/Inquiry";
            if(a != 0){
                msg = "정상적으로 삭제되었습니다";
            } else{
                msg = "알 수 없는 오류로 인해 삭제가 중단되었습니다. 1:1 문의 메인 창으로 돌아갑니다.";
            }
            w.write("<script>alert('"+msg+"');location.href='"+url+"';</script>");
        }
    }

    @PostMapping("/Inquiry/detail")
    public String InquiryDetail(@RequestParam("mid") String mid,@RequestParam("qno") int qno,
                                 Authentication authentication,Model model,
                                HttpServletResponse response) throws IOException {
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        String username = userDetails.getUsername(); // user 아이디 확인
        String u0 = inquiryService.getU0(username);// 아이디를 이용하여 해당 사용자가 관리자인지 확인.

        // 접속을 시도하는 유저의 nick과 1:1 문의를 등록한 유저의 nick를 비교하기 위함.

        if(username.equals(mid) || u0.equals("u0")){
            InquiryVO inquiryVO = new InquiryVO();
            inquiryVO.setMid(mid);
            inquiryVO.setQno(qno);
            List<InquiryVO> basket = inquiryService.InquiryDetail(inquiryVO);
            model.addAttribute("list",basket);
            model.addAttribute("u0", u0);
            model.addAttribute("username", username);
            return "contents/support/detail";
        } else {
            response.setContentType("text/html; charset=utf-8");
            response.setCharacterEncoding("UTF-8");
            String msg = "다른 사람의 문의 내용을 확인할 수 없습니다.";
            String url = "/Inquiry";
            PrintWriter w = response.getWriter();
            w.write("<script>alert('"+msg+"');location.href='"+url+"';</script>");
        }

        return "contents/index";
    }

    @PostMapping("/Inquiry/inqResult")
    public void inqResult(@RequestParam("qanst") String qanst,
                          @RequestParam("qans") String qans,
                          @RequestParam("nick") String nick,
                          @RequestParam("a0") String a0,
                          @RequestParam("qno") int qno,
                          Model model,
                          Authentication authentication,
                          HttpServletResponse response) throws Exception{
        InquiryVO inquiryVO = new InquiryVO();
        inquiryVO.setQans(qans);
        inquiryVO.setNick(nick);
        inquiryVO.setA0(a0);
        inquiryVO.setQanst(qanst);
        inquiryVO.setQno(qno);

        int a = inquiryService.insertInqAns(inquiryVO);
        response.setContentType("text/html; charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        String url = "/Inquiry";
        PrintWriter w = response.getWriter();
        String msg;
        if(a != 0){
            msg = "정상적으로 등록되었습니다";
        }else{
            msg = "답변 등록 중 오류가 발생했습니다. 개발자에게 문의 부탁드립니다.";
        }
        w.write("<script>alert('"+msg+"');location.href='"+url+"';</script>");
    }
}
