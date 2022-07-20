package com.co.algomoko.diary.domain;

import lombok.Data;

import java.util.*;

import org.springframework.format.annotation.DateTimeFormat;

@Data
public class DiaryVO {
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
    private int carb;
    private int prot;
    private int fat;
    private int sumcal;
	
    
}
