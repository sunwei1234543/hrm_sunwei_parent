package com.sunwei.hrm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class Es9004Application {
    public static void main(String[] args) {
        SpringApplication.run(Es9004Application.class,args);
    }
}