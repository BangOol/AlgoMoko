package com.co.algomoko.admin.service;

import com.co.algomoko.admin.domain.AdminVO;
import com.co.algomoko.admin.mapper.AdminMapper;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AdminServiceImpl implements AdminService {

    @Setter(onMethod_ = {@Autowired})
    AdminMapper adminMapper;

    @Override
    public List<AdminVO> makeList() {
        return adminMapper.makeList();
    }

    @Override
    public List<AdminVO> findUserList(AdminVO adminVO){
        return adminMapper.findUserList(adminVO);
    }

}
