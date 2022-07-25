package com.co.algomoko.support.service;

import com.co.algomoko.admin.paging.PaginationUser;

import java.util.List;
import java.util.Map;

public interface InqPagingService {
    public int inqTableCount() throws Exception;

    // 1:1 문의 해당 페이지 테이블 출력
    public List<Map<String, Object>> inqAllList(PaginationUser paginationUser) throws Exception;
}
