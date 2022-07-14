package com.co.algomoko.support.domain;

import lombok.Data;

import java.sql.Date;

@Data
public class NoticeVO {
    int nno;
    String ntitle;
    Date ndate;
    String ncon;
    String nfile;
    int nhit;
}
