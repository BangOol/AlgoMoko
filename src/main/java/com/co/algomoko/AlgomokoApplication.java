package com.co.algomoko;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.co.algomoko.**.mapper")
public class AlgomokoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlgomokoApplication.class, args);
    }

}
