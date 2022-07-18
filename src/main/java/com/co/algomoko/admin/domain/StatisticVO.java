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


}
