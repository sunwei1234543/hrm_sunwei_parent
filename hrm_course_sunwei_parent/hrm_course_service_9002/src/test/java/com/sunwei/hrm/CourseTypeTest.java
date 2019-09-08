package com.sunwei.hrm;

import com.sunwei.hrm.domain.CourseType;
import com.sunwei.hrm.service.impl.CourseTypeServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Course9002Application.class)
public class CourseTypeTest {
    @Autowired
    private CourseTypeServiceImpl courseTypeService;
    @Test
    public void testQueryTreeData(){
        List<CourseType> courseTypes = courseTypeService.queryTypeTree(null);
        for (CourseType courseType : courseTypes) {
            System.out.println(courseType);
            List<CourseType> children = courseType.getChildren();
            if(children!=null){
                for (CourseType child : children) {
                    System.out.println(child);
                }
            }
        }
    }
}
