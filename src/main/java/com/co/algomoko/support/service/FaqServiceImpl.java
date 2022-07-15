package com.co.algomoko.support.service;

import com.co.algomoko.support.domain.FaqVO;
import com.co.algomoko.support.mapper.FaqMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FaqServiceImpl implements FaqService {

    @Autowired FaqMapper faqMapper;

    @Override
    public List<FaqVO> FAQList(FaqVO faqVo) {
        return faqMapper.FAQList(faqVo);
    }
}
