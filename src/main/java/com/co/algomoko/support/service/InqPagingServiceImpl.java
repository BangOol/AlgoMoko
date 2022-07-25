package com.co.algomoko.support.service;

import com.co.algomoko.admin.paging.PaginationUser;
import com.co.algomoko.support.mapper.FaqMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class InqPagingServiceImpl implements InqPagingService {

    @Autowired
    FaqMapper faqMapper;
    @Override
    public int inqTableCount() throws Exception{
        return faqMapper.inqTableCount();
    }

    @Override
    public List<Map<String, Object>> inqAllList(PaginationUser paginationUser) throws Exception{
        return faqMapper.inqAllList(paginationUser);
    }
}
