package com.co.algomoko.admin.service;

import com.co.algomoko.admin.domain.AdminVO;
import com.co.algomoko.admin.domain.StatisticVO;
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
    public List<AdminVO> findUserList(AdminVO adminVO){
        return adminMapper.findUserList(adminVO);
    }

    @Override
    public List<AdminVO> findDetailUser(AdminVO adminVO) {
        return adminMapper.findDetailUser(adminVO);
    }

    @Override
    public String insertRestrict(AdminVO adminVO) {
        return adminMapper.insertRestrict(adminVO);
    }

//    @Override
//    public List<AdminVO> findBlackList() {
//        return adminMapper.findBlackList();
//    }
//
//    @Override
//    public List<AdminVO> findRestrictList() {
//        return adminMapper.findRestrictList();
//    }

    @Override
    public List<StatisticVO> genderRate() {
        return adminMapper.genderRate();
    }

    @Override
    public List<StatisticVO> RestrictedRate() {
        return adminMapper.RestrictedRate();
    }

    @Override
    public List<StatisticVO> heightRate() {
        return adminMapper.heightRate();
    }
    @Override
    public List<StatisticVO> ageRate(){
        return adminMapper.ageRate();
    }

    @Override
    public List<StatisticVO> bmiRate() {
        return adminMapper.bmiRate();
    }


}
