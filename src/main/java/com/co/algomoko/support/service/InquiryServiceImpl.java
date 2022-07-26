package com.co.algomoko.support.service;

import com.co.algomoko.support.domain.InquiryVO;
import com.co.algomoko.support.mapper.FaqMapper;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InquiryServiceImpl implements InquiryService{

    @Setter(onMethod_ = {@Autowired})
    private FaqMapper faqMapper;


    @Override
    public List<InquiryVO> searchNo() {
        return faqMapper.searchNo();
    }
    @Override
    public List<InquiryVO> list(){
        return faqMapper.list();
    }
    @Override
    public void insertInquiry(InquiryVO inquiryVO){
        faqMapper.insertInquiry(inquiryVO);
    }
    @Override
    public String getNick(String username){
        return faqMapper.getNick(username);
    }

    @Override
    public String getU0(String mid){
        return faqMapper.getU0(mid);
    }

    @Override
    public List<InquiryVO> InquiryDetail(InquiryVO inquiryVO){
        return faqMapper.InquiryDetail(inquiryVO);
    }
}
