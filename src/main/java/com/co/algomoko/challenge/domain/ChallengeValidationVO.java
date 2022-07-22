package com.co.algomoko.challenge.domain;

import lombok.Data;

@Data
public class ChallengeValidationVO extends MyChallengeVO {
	private int cvno;
	private int cno3;
	private String cvimg;
	private String cvdate;
	private int round;
	private String attyn;
}
