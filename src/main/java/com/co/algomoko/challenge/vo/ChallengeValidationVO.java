package com.co.algomoko.challenge.vo;

import java.sql.Clob;
import java.util.Date;

import lombok.Data;

@Data
public class ChallengeValidationVO {
	private int cvno;
	private int mycno;
	private Clob cvimg;
	private Date cvdate;
}
