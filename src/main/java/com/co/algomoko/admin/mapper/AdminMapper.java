package com.co.algomoko.admin.mapper;


import com.co.algomoko.admin.domain.AdminVO;
import com.co.algomoko.admin.domain.StatisticVO;
import com.co.algomoko.admin.paging.PaginationUser;

import java.util.List;
import java.util.Map;

public interface AdminMapper {
    public List<AdminVO> makeList();

    public List<AdminVO> findUserList(AdminVO adminVO);

    public List<AdminVO> findDetailUser(AdminVO adminVO);

    public String insertRestrict(AdminVO adminVO);

    List<AdminVO> findBlackList();

    List<AdminVO> findRestrictList();

    List<StatisticVO> genderRate(); // 유저 성별 통계

    List<StatisticVO> RestrictedRate(); // 제한된 유저 통계

    List<StatisticVO> heightRate();// 회원 별 키 통계

    List<StatisticVO> ageRate(); // 회원 나이 통계
    List<StatisticVO> bmiRate(); // 회원 BMI 통계


    // 페이징
    public List<Map<String, Object>> SelectAllList() throws Exception;

    //Paging
    public List<Map<String, Object>> SelectAllList(PaginationUser paginationUser) throws Exception;

    //count
    public int TableCount() throws Exception;
}
