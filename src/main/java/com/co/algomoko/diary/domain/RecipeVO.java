package com.co.algomoko.diary.domain;

import lombok.Data;


@Data
public class RecipeVO {
	private int rno;
	private String mid;
    private String rname;
    private String rnick;
    private int amount;
    
    private int cal;
    private int carb;
    private int prot;
    private int rfat;
    private String rrecipe;
    private String unitcd;
    private int hit;
	
    
}
