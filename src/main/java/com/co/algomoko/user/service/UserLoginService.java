package com.co.algomoko.user.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.co.algomoko.user.domain.Account;
import com.co.algomoko.user.domain.UserVO;
import com.co.algomoko.user.service.impl.UserServiceImpl;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserLoginService implements UserDetailsService{
    @Autowired
    private UserService ser;



    @Override
    public UserDetails loadUserByUsername(String mid) throws UsernameNotFoundException{
        UserVO vo = ser.findId(mid);
        if(vo==null)
            throw new InternalAuthenticationServiceException("사용자를 찾을 수 없습니다");

        Account ac = Account.builder().mid(mid).mpw(vo.getMpw()).enabled(vo.getEnabled()).build();

        String authority = vo.getAuthority();
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(authority));
        ac.setAuthorities(authorities);

        return ac;

    }

}