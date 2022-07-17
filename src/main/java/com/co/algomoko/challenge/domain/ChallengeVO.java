package com.co.algomoko.challenge.domain;

import java.sql.Clob;

import lombok.Data;

@Data
public class ChallengeVO {
	private int cno;
	private String ctitle;
	private String ccon;
	private int cdday;
	private Clob cimg;
}
