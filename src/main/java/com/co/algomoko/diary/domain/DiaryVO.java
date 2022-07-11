package com.co.algomoko.diary.domain;

import lombok.Data;

import java.sql.Date;

@Data
public class DiaryVO {
	private int ddno;
	private String uid;
    private Date ddate;
    private String ucon;
    private Date dddo;
    private String ddrena;
    private String ddname;
    private String f0;
    private int amount;
    private int cal;
    private int carb;
    private int prot;
    private int fat;
    
}
