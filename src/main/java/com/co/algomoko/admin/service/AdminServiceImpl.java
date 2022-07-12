package com.co.algomoko.admin.service;

import com.co.algomoko.admin.domain.AdminVO;
import com.co.algomoko.admin.mapper.AdminMapper;
import com.co.algomoko.user.domain.UserVO;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AdminServiceImpl implements AdminService {

    @Setter(onMethod_ = {@Autowired})
    AdminMapper adminMapper;

    @Override
    public List<UserVO> makeList() {
        return adminMapper.makeList();
    }

    @Override
    public List<UserVO> findUser() {
        return null;
    }

}
