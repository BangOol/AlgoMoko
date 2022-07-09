package com.co.algomoko.user.service;

import com.co.algomoko.user.domain.UserVO;
import com.co.algomoko.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public class UserServiceImpl implements UserService{
    UserMapper userMapper;

    @Override
    public String testUser() {
        return userMapper.testUser();
    }
}
