package com.co.algomoko.food.domain;

import lombok.Data;

@Data
public class FoodVO {
	private String ing; //음식명
	private int cal; // 칼로리
	private int amount; // 
	private int carb; // 탄수화물
	private int prot; // 단백질
	private int fat; // 지방
	private String f0; // 단위('g' or 'ml')
	private int weight; // 1회당 제공량
}
