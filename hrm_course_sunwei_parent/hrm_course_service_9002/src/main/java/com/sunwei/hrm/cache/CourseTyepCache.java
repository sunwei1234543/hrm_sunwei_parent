package com.sunwei.hrm.cache;

import com.alibaba.fastjson.JSONArray;
import com.sunwei.hrm.RedisClient;
import com.sunwei.hrm.domain.CourseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CourseTyepCache {

    @Autowired
    private RedisClient redisClient;

    private static final String TYPETREEDATA_IN_REDIS="typeTreeData_in_redis";
    //从reids 获取数据
    public List<CourseType> getCourseTypes() {
        String redisData = redisClient.get(TYPETREEDATA_IN_REDIS);
        return  JSONArray.parseArray(redisData,CourseType.class);
    }

    //设置数据到redis
    public void setCourseTypes(List<CourseType> courseTypesDb) {
        String jsonStr = JSONArray.toJSONString(courseTypesDb);
        redisClient.set(TYPETREEDATA_IN_REDIS,jsonStr);
    }
}
