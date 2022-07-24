package com.co.algomoko.support.service;

import com.co.algomoko.support.domain.InquiryVO;

import java.util.List;

public interface InquiryService {
    List<InquiryVO> searchNo();

    void insertInquiry(String title, String content);
    List<InquiryVO> list();

}
