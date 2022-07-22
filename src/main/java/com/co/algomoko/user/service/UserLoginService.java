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
import com.co.algomoko.user.mapper.UserMapper;
import com.co.algomoko.user.service.impl.UserServiceImpl;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserLoginService implements UserDetailsService{
    @Autowired
    private UserService ser;

    

    @Override
    public UserDetails loadUserByUsername(String mid) throws UsernameNotFoundException{
//        UserVO vo = ser.findId(mid);
//        if(vo==null)
//            throw new InternalAuthenticationServiceException("사용자를 찾을 수 없습니다");
//
//        Account ac = Account.builder().mid(mid).mpw(vo.getMpw()).enabled(vo.getEnabled()).build();
//
//        Collection<GrantedAuthority> authorities = new ArrayList<>();
//        String authority = vo.getAuthority();
//        authorities.add(new SimpleGrantedAuthority(authority));
//        ac.setAuthorities(authorities);
////        if("admin".equals(mid)) {
////        	
////        	
////        }
//        return ac;
//
    	Account ac;
    	ac = ser.findId1(mid);
    	UserVO vo = ser.findId(mid);
    	
    	if (vo == null){
            throw new UsernameNotFoundException("User not authorized.");
        }
    	
    	if (ac == null){
            throw new UsernameNotFoundException("User not authorized.");
        }
    	ac = Account.builder().mid(mid).mpw(vo.getMpw()).build();
    	Collection<GrantedAuthority> authorities = new ArrayList<>();
    	String authority = vo.getU0();
    	authorities.add(new SimpleGrantedAuthority(authority));
    	ac.setU0(authority);
    	return ac;
    }

}