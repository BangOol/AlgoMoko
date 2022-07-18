package com.co.algomoko.admin.mapper;


import com.co.algomoko.admin.domain.AdminVO;
import com.co.algomoko.user.domain.UserVO;
import org.apache.catalina.User;

import java.util.List;

public interface AdminMapper {
    public List<AdminVO> makeList();

    public List<AdminVO> findUserList(AdminVO adminVO);

    public List<AdminVO> findDetailUser(AdminVO adminVO);

    public String insertRestrict(AdminVO adminVO);
}
