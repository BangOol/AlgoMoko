package com.co.algomoko.challenge.vo;

import java.sql.Clob;

import lombok.Data;

@Data
public class ChallengeVO {
	private String cno;
	private String ctitle;
	private String ccon;
	private int cdday;
	private Clob cimg;
}
