package com.sunwei.hrm.service;

import com.sunwei.hrm.domain.CourseType;
import com.baomidou.mybatisplus.service.IService;
import com.sunwei.hrm.query.CourseTypeQuery;
import com.sunwei.hrm.util.PageList;

import java.util.List;

/**
 * <p>
 * 课程目录 服务类
 * </p>
 *
 * @author sunwei
 * @since 2019-09-07
 */
public interface ICourseTypeService extends IService<CourseType> {

    /**
     * 分页+高级查询+关联查询
     */
    PageList<CourseType> selectListPage(CourseTypeQuery query);

    /**
     * 通过父亲id获取儿子，及其儿子的子子孙孙
     * @param pid
     * @return
     */
    List<CourseType> queryTypeTree(Long pid);
}
