package com.sunwei.hrm.service.impl;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sunwei.hrm.client.EsCourseClient;
import com.sunwei.hrm.client.doc.EsCourse;
import com.sunwei.hrm.domain.Course;
import com.sunwei.hrm.mapper.CourseDetailMapper;
import com.sunwei.hrm.mapper.CourseMapper;
import com.sunwei.hrm.query.CourseQuery;
import com.sunwei.hrm.service.ICourseService;
import com.sunwei.hrm.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sunwei
 * @since 2019-09-07
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements ICourseService {
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private EsCourseClient esCourseClient;
    @Autowired
    private CourseDetailMapper courseDetailMapper;
    @Override
    public boolean deleteById(Serializable id) {
        //删除数据库
        courseMapper.deleteById(id);
        //判断状态 还要删除索引库
        Course course = courseMapper.selectById(id);
        if(course.getStatus()==1){
            esCourseClient.delete(Integer.valueOf(id.toString()));
            return true;
        }
        return true;
    }

    @Override
    public boolean updateById(Course entity) {
        //修改数据库
        courseMapper.updateById(entity);
        //判断状态 还要修改索引库
        Course course = courseMapper.selectById(entity.getId());
        if(course.getStatus()==1){
            esCourseClient.save(course2EsCourse(entity));
            return true;
        }
        return true;
    }
    @Override
    public boolean insert(Course entity) {
        //课程表
        entity.setStatus(0); // tenantId tenantName userId userName
        courseMapper.insert(entity);
        //课程详情
        entity.getCourseDetail().setCourseId(entity.getId());
        courseDetailMapper.insert(entity.getCourseDetail());
        return true;
    }


    private List<EsCourse> courseList2EsCourse(List<Course> courseList) {
        List<EsCourse> result = new ArrayList<>();
        for (Course course : courseList) {
            result.add(course2EsCourse(course));
        }
        return result;
    }

    // @TODO 不同服务,反3Fn设计冗余字段
// @TODO 相同服务,关联查询
    private EsCourse course2EsCourse(Course course) {
        EsCourse  result = new EsCourse();
        result.setId(course.getId());
        result.setName(course.getName());
        result.setUsers(course.getUsers());
        result.setCourseTypeId(course.getCourseTypeId());
        //type-同库
        if (course.getCourseType() != null)
            result.setCourseTypeName(course.getCourseType().getName());
        //跨服务操作
        result.setGradeId(course.getGrade());
        result.setGradeName(null);
        result.setStatus(course.getStatus());
        result.setTenantId(course.getTenantId());
        result.setTenantName(course.getTenantName());
        result.setUserId(course.getUserId());
        result.setUserName(course.getUserName());
        result.setStartTime(course.getStartTime());
        result.setEndTime(course.getEndTime());
        //Detail
        result.setIntro(null);
        //resource
        result.setResources(null);
        //market
        result.setExpires(null);
        result.setPrice(null);
        result.setPriceOld(null);
        result.setQq(null);
        return result;
    }

    //课程上线
    @Override
    public void onLine(Long[] ids) {
        //在索引库批量添加数据
        List<Course> courses = courseMapper.selectBatchIds(Arrays.asList(ids));
        List<EsCourse> esCourses = courseList2EsCourse(courses);
        esCourseClient.batchSave(esCourses);
        //批量修改状态
        //update t_course set status = 1,start_time=xxx where id in (1,2,3)
        courseMapper.batchOnline(Arrays.asList(ids));

    }
    //课程下线
    @Override
    public void offLine(Long[] ids) {
        //在索引库批量删除数据
        List<Course> courses = courseMapper.selectBatchIds(Arrays.asList(ids));
        List<EsCourse> esCourses = courseList2EsCourse(courses);
        esCourseClient.batchDel(esCourses);
        //批量修改状态
        //update t_course set status = 1,start_time=xxx where id in (1,2,3)
        courseMapper.batchOffline(Arrays.asList(ids));

    }
    @Override
    public PageList<Course> selectListPage(CourseQuery query) {
        Page<Course> page = new Page<>(query.getPage(),query.getRows());
        List<Course> rows =  courseMapper.loadListPage(page,query);
        return new PageList<>(page.getTotal(),rows);
    }

}
