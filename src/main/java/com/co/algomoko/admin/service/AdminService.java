package com.co.algomoko.admin.service;

import com.co.algomoko.admin.domain.AdminVO;
import com.co.algomoko.user.domain.UserVO;

import java.util.List;

public interface AdminService {
    public List<AdminVO> makeList();
    List<AdminVO> findUserList(AdminVO adminVO);
    List<AdminVO> findDetailUser(AdminVO adminVO);

    String insertRestrict(AdminVO adminVO);
}
