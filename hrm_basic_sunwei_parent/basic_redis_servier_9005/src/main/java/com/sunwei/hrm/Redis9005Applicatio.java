package com.sunwei.hrm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class Redis9005Applicatio {
    public static void main(String[] args) {
        SpringApplication.run(Redis9005Applicatio.class,args);
    }
}
