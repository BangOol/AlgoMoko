package com.co.algomoko.user.domain;

import lombok.Data;

import java.sql.Date;

@Data
public class AlarmVO {
    private int ano;
    private String uid;
    private Date adate;
    private String atitle;
    private String acontent;
    private Date atime;
}
