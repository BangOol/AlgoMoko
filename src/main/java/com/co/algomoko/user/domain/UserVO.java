package com.co.algomoko.user.domain;

import lombok.Data;

import java.sql.Date;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
public class UserVO {
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
    private Boolean enabled;
    private String authority;

}

