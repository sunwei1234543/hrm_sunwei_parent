package com.sunwei.hrm.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.sunwei.hrm.domain.Course;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sunwei.hrm.query.CourseQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author sunwei
 * @since 2019-09-07
 */
public interface CourseMapper extends BaseMapper<Course> {

    /*批量上线*/
    void batchOnline(List<Long> asList);
    /*批量下线*/
    void batchOffline(List<Long> asList);

    List<Course> loadListPage(Page<Course> page,@Param("query") CourseQuery query);
}
