package com.co.algomoko.user.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account implements UserDetails{
    private String mid;
    private String mpw;
    private String nick;
    //private Boolean enabled;
//    private Collection<GrantedAuthority> authorities;
    //권한 !!
    private String U0;
    
    
    
    
    
  
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
    	 List<GrantedAuthority> auth = new ArrayList<>();
        //return Collections.singletonList(new SimpleGrantedAuthority(this.U0));
    	 
        auth.add(new SimpleGrantedAuthority(U0));
       // auth.add(new SimpleGrantedAuthority(Role.class.getCanonicalName());
        return auth;
    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return mpw;
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return mid;
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

	

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }

}