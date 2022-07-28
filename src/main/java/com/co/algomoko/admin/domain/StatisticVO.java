package com.co.algomoko.admin.domain;

import lombok.Data;

@Data
public class StatisticVO {
    // 성별 비율
    double totalRate;
    double maleRate;
    double femaleRate;

    // 회원 제한 비율
    double b0;
    double b1;
    double b2;
    double b3;

    //회원 키 비율
    double range12;
    double range13;
    double range14;
    double range15;
    double range16;
    double range17;
    double range18;
    double range19;
    double range20;
    double range21;

    // 회원 나이 비율
    double age10;
    double age20;
    double age30;
    double age40;
    double age50;
    double age60;
    double age70;
    double age80;

    // 회원 BMI 비율
    double bmi1; // BMI : 5 ~ 10
    double bmi2;
    double bmi3;
    double bmi4;
    double bmi5;
    double bmi6;
    double bmi7;

}
