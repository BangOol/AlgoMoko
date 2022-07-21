package com.co.algomoko.admin.service;

import com.co.algomoko.admin.mapper.AdminMapper;
import com.co.algomoko.admin.paging.PaginationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PagingServiceImpl implements PagingService{

    @Autowired
    AdminMapper adminMapper;



    @Override
    public List<Map<String, Object>> SelectAllList(PaginationUser paginationUser) throws Exception {
        return adminMapper.SelectAllList(paginationUser);
    }

    @Override
    public int TableCount() throws Exception {
        return adminMapper.TableCount();
    }
}
