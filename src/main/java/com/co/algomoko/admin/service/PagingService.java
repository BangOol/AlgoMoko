package com.co.algomoko.admin.service;

import com.co.algomoko.admin.domain.AdminVO;
import com.co.algomoko.admin.paging.PaginationUser;

import java.util.List;
import java.util.Map;

public interface PagingService {
    //Paging
    public List<Map<String, Object>> SelectAllList(PaginationUser paginationUser) throws Exception;
    //count
    public int TableCount(PaginationUser paginationUser) throws Exception;

    public int TableCountAll() throws Exception;


}
