package com.co.algomoko.support.service;

import com.co.algomoko.support.domain.InquiryVO;

import java.util.List;

public interface InquiryService {
    List<InquiryVO> searchNo();

    void insertInquiry(InquiryVO inquiryVO);
    List<InquiryVO> list();

    String getNick(String username);
    
    String getU0(String mid); // u0 값 가져오기

    List<InquiryVO> InquiryDetail(InquiryVO inquiryVO);

    int insertInqAns(InquiryVO inquiryVO);

}
