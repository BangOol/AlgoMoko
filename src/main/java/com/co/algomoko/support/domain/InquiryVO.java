package com.co.algomoko.support.domain;

import lombok.Data;

import java.sql.Date;

@Data
public class InquiryVO {
    // 검색 필터
    private String type;
    private String keyword;

    int qno;
    String C0;
    String mid;
    String nick;
    String qtitle;
    String qcon;
    Date qdate;
    Date ansdate;
    String qanst;
    String qans;
    String A0;

}
