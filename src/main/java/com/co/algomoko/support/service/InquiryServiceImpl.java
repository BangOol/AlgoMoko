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
    public void insertInquiry(String title, String content){
        faqMapper.insertInquiry(title, content);
    }
}
