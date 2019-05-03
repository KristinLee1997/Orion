package com.aries.orion;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.aries.orion.mapper")
public class OrionApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrionApplication.class, args);
    }
}
