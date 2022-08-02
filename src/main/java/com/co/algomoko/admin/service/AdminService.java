package com.co.algomoko.admin.service;

import com.co.algomoko.admin.domain.AdminVO;
import com.co.algomoko.admin.domain.StatisticVO;
import com.co.algomoko.user.domain.UserVO;

import java.util.List;

public interface AdminService {
    List<AdminVO> findUserList(AdminVO adminVO);
    List<AdminVO> findDetailUser(AdminVO adminVO);
    int insertRestrict(AdminVO adminVO); // 회원 상태 제한
//    List<AdminVO> findBlackList();
//    List<AdminVO> findRestrictList();
    List<StatisticVO> genderRate();
    List<StatisticVO> RestrictedRate();
    List<StatisticVO> heightRate(); // 회원 별 키 비율

    List<StatisticVO> ageRate(); // 회원 나이 비율

    List<StatisticVO> bmiRate(); // 회원 BMI 비율


}
