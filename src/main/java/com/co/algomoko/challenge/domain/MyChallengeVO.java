package com.co.algomoko.challenge.domain;

import java.util.Date;

import lombok.Data;
import lombok.Getter;

@Data
public class MyChallengeVO extends ChallengeVO {
	private int mycno;
	private int cno2;
	private String uid;
	private int cper;
	private String sdate;
	private String edate;
}
