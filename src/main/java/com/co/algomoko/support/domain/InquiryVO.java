package com.co.algomoko.support.domain;

import lombok.Data;

import java.sql.Date;

@Data
public class InquiryVO {
    int qno;
    String C0;
    String mid;
    String nick;
    String qtitle;
    String qcon;
    Date qdate;
    String qfile;
    String qans;
    String A0;

}
