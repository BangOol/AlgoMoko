package com.co.algomoko.admin.service;

import com.co.algomoko.admin.paging.PaginationUser;

import java.util.List;
import java.util.Map;

public interface PagingService {
    //select * from Test_Table
    public List<Map<String, Object>> SelectAllList() throws Exception;

    //Paging
    public List<Map<String, Object>> SelectAllList(PaginationUser paginationUser) throws Exception;

    //count
    public int TableCount() throws Exception;
}
