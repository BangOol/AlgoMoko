package com.co.algomoko.admin.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.sql.Date;



@Data
public class AdminVO {
    // 검색 필터
    private String type;
    private String keyword;

    // 회원 필터
    private String uid;
    private String upw;
    private String uname;
    private String nick;
    private Date birth;
    private String sex;
    private String height;
    private int weight;
    private int bmi;
    private int tcal;
    private int tweight;
    private String U0;
    private String B0;
}
