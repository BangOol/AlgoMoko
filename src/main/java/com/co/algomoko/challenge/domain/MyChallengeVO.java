package com.co.algomoko.challenge.domain;

import java.util.Date;

import lombok.Data;
import lombok.Getter;

@Data
public class MyChallengeVO {
	private int mycno;
	private int cno2;
	private String uid;
	private int cper;
	private Date sdate;
	private Date edate;
}
