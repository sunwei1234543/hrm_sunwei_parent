package com.sunwei.hrm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class eureka7001Application {
    public static void main(String[] args) {
        SpringApplication.run(eureka7001Application.class,args);
    }
}
