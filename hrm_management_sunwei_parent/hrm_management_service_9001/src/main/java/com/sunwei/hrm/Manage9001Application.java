package com.sunwei.hrm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.sunwei.hrm.mapper")
public class Manage9001Application {
    public static void main(String[] args) {
        SpringApplication.run(Manage9001Application.class,args);
    }
}
