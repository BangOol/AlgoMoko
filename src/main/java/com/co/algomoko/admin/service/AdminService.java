package com.co.algomoko.admin.service;

import com.co.algomoko.admin.domain.AdminVO;
import com.co.algomoko.user.domain.UserVO;

import java.util.List;

public interface AdminService {
    public List<UserVO> makeList();

    public List<UserVO> findUser();
}
