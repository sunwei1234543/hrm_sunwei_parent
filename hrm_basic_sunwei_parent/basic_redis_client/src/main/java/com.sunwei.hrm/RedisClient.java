package com.sunwei.hrm;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "HRM-REDIS",
        fallbackFactory = RedisClientFallbackFactory.class )//服务提供者的名称
@RequestMapping("/cache")
public interface RedisClient {

    @PostMapping
    void set(@RequestParam("key")String key, @RequestParam("value")String value);
    @GetMapping
    String get(@RequestParam("key")String key);

}