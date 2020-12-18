package com.xys.timemgr;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan
public class TimemgrApplication {

    public static void main(String[] args) {
        SpringApplication.run(TimemgrApplication.class, args);
    }

}
