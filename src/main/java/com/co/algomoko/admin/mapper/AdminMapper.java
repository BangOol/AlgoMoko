package com.co.algomoko.admin.mapper;


import com.co.algomoko.user.domain.UserVO;
import org.apache.catalina.User;

import java.util.List;

public interface AdminMapper {
    public List<UserVO> makeList();
}
