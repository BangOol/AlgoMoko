package com.co.algomoko.challenge.domain;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ChallengeVO {
	
	private String cno;
	private String ctitle;
	private String ccon;
	private int cdday;
	private String filepath;
	private String filename;
}
