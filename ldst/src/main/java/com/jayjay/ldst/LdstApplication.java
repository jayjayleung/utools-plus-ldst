package com.jayjay.ldst;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.jayjay.ldst.mapper")
public class LdstApplication {

    public static void main(String[] args) {
        SpringApplication.run(LdstApplication.class, args);
    }

}
