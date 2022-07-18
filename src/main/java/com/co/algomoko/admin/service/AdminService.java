package com.co.algomoko.admin.service;

import com.co.algomoko.admin.domain.AdminVO;
import com.co.algomoko.admin.domain.StatisticVO;
import com.co.algomoko.user.domain.UserVO;

import java.util.List;

public interface AdminService {
    public List<AdminVO> makeList();
    List<AdminVO> findUserList(AdminVO adminVO);
    List<AdminVO> findDetailUser(AdminVO adminVO);
    String insertRestrict(AdminVO adminVO);

    List<AdminVO> findBlackList();
    List<AdminVO> findRestrictList();

    List<StatisticVO> genderRate();
    List<StatisticVO> RestrictedRate();

    List<StatisticVO> heightRate(); // 회원 별 키 비율
}
