package com.co.algomoko.challenge.domain;

import lombok.Data;

@Data
public class ChallengeVO{
	
	private String cno; 			// 챌린지 번호
	private String mid; 			// 유저 아이디
	private String ctitle; 			// 챌린지 이름
	private String ccon; 			// 챌린지 내용
	private int cdday; 				// 챌린지 디데이
	private String filepath; 		// 챌린지 파일경로
	private String filename; 		// 챌린지 파일 이름
	private int firstRecordIndex; 	// SQL의 조건절에 사용되는 첫번째 ROW_NUM	 
	private int lastRecordIndex; 	//SQL의 조건절에 사용되는 마지막 ROW_NUM
	
}
