package com.co.algomoko.support.mapper;

import com.co.algomoko.admin.paging.PaginationUser;
import com.co.algomoko.support.domain.FaqVO;
import com.co.algomoko.support.domain.InquiryVO;
import com.co.algomoko.support.service.FaqService;

import java.util.List;
import java.util.Map;

public interface FaqMapper {
    public List<FaqVO> FAQList(FaqVO faqVo); // 클릭한 유형 테이블

    // Inquiry(1:1문의)
    public List<InquiryVO> searchNo();

    public List<InquiryVO> list();

    public void insertInquiry(InquiryVO inquiryVO); // 1:1 문의 등록
    
    public int inqTableCount(); // 1:1문의 전체 테이블 수

    public List<Map<String, Object>> inqAllList(PaginationUser paginationUser);

    public String getNick(String username); // 아이디와 일치하는 유저 닉네임 가져오기

    public String getU0(String mid); // 아이디와 일치하는 유저 U0 가져오기

    public List<InquiryVO> InquiryDetail(InquiryVO inquiryVO);

    public int insertInqAns(InquiryVO inquiryVO);

    public int updateInquiry(InquiryVO inquiryVO);

    public int deleteInquiry(InquiryVO inquiryVO);
}
