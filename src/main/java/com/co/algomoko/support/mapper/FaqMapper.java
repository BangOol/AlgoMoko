package com.co.algomoko.support.mapper;

import com.co.algomoko.support.domain.FaqVO;
import com.co.algomoko.support.domain.InquiryVO;
import com.co.algomoko.support.service.FaqService;

import java.util.List;

public interface FaqMapper {
    public List<FaqVO> FAQList(FaqVO faqVo); // 클릭한 유형 테이블

    // Inquiry(1:1문의)
    public List<InquiryVO> searchNo();

    public List<InquiryVO> list();

    public void insertInquiry(String title, String content);
}
