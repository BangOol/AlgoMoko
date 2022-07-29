package com.co.algomoko.diary.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
@Data
public class DiaryVO2 {
	private int ddno;
	private String mid;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ddate;
    private String ucon;
    private String dddo;
    private String ddrena;
    private String ddname;
    private String f0;
    private String dday;
    private int amount;
    private int cal;
    private int carb=1;
    private int prot=1;
    private int fat=1;
    private int sumcal;
    private String nick;
}
