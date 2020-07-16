package com.ly.imallbatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.ly.imallbatis.dao")
public class ImallbatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImallbatisApplication.class, args);
    }

}
