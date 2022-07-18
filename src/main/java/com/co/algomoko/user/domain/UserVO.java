package com.co.algomoko.user.domain;

import lombok.Data;

import java.sql.Date;
import java.util.Collection;

/*import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
*/
@Data
public class UserVO {
	//implements UserDetail;
    private String mid;
    private String mpw;
    private String uname;
    private String nick;
    private Date birth;
    private String sex;
    private String height;
    private int weight;
    private int bmi;
    private int tcal;
    private Integer tweight;
    private String U0;
    private String B0;
    private UserGrade grade;
//    
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		return this.getAuthorities();
//	}
//	@Override
//	public String getPassword() {
//		// TODO Auto-generated method stub
//		return this.getPassword();
//	}
//	@Override
//	public String getUsername() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	@Override
//	public boolean isAccountNonExpired() {
//		// TODO Auto-generated method stub
//		return false;
//	}
//	@Override
//	public boolean isAccountNonLocked() {
//		// TODO Auto-generated method stub
//		return false;
//	}
//	@Override
//	public boolean isCredentialsNonExpired() {
//		// TODO Auto-generated method stub
//		return false;
//	}
//	@Override
//	public boolean isEnabled() {
//		// TODO Auto-generated method stub
//		return false;}
	}

