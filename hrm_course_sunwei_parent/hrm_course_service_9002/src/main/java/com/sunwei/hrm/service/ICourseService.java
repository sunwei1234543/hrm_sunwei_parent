package com.sunwei.hrm.service;

import com.sunwei.hrm.domain.Course;
import com.baomidou.mybatisplus.service.IService;
import com.sunwei.hrm.query.CourseQuery;
import com.sunwei.hrm.util.PageList;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sunwei
 * @since 2019-09-07
 */
public interface ICourseService extends IService<Course> {

    void onLine(Long[] ids);

    void offLine(Long[] ids);

    PageList<Course> selectListPage(CourseQuery query);
}
