package com.co.algomoko.challenge.domain;

import java.util.Date;

import lombok.Data;

@Data
public class MyChallengeVO extends ChallengeVO {
	private int mycno;
	private int cno2;
	private String mid;
	private int cper;
	private String sdate;
	private String edate;
	private int round;
	private String vcon;
}
