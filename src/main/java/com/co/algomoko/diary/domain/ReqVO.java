package com.co.algomoko.diary.domain;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ReqVO {

	private List<DiaryVO> diarys;
	private String dddo;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date ddate;
	private String ucon;
	
	
	
}
