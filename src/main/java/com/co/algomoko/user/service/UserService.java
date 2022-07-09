package com.co.algomoko.user.service;

import com.co.algomoko.user.domain.UserVO;
import com.co.algomoko.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public interface UserService {
    String testUser();
}
