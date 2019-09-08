package com.sunwei.hrm.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.sunwei.hrm.domain.CourseType;
import com.sunwei.hrm.query.CourseTypeQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 课程目录 Mapper 接口
 * </p>
 *
 * @author sunwei
 * @since 2019-09-07
 */
public interface CourseTypeMapper extends BaseMapper<CourseType> {

    /**
     * 高级查询+分页+关联查询
     * @param page
     * @param query
     * @return
     */
    List<CourseType> loadListPage(Pagination page,@Param("query") CourseTypeQuery query);
}
