package com.co.algomoko.admin.domain;

import lombok.Data;

@Data
public class StatisticVO {
    // 성별 비율
    int totalRate;
    int maleRate;
    int femaleRate;

    // 회원 제한 비율
    String b0;
    String b1;
    String b2;
    String b3;

    //회원 키 비율
    int range12;
    int range13;
    int range14;
    int range15;
    int range16;
    int range17;
    int range18;
    int range19;
    int range20;
    int range21;

    // 회원 나이 비율
    int age10;
    int age20;
    int age30;
    int age40;
    int age50;
    int age60;
    int age70;
    int age80;

    // 회원 BMI 비율
    int bmi1; // BMI : 5 ~ 10
    int bmi2;
    int bmi3;
    int bmi4;
    int bmi5;
    int bmi6;
    int bmi7;

}
