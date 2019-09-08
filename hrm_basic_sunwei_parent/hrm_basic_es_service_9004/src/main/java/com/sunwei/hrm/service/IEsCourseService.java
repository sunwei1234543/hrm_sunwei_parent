package com.sunwei.hrm.service;


import com.sunwei.hrm.doc.EsCourse;
import com.sunwei.hrm.query.EsCourseQuery;
import com.sunwei.hrm.util.PageList;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sunwei
 * @since 2019-09-05
 */
public interface IEsCourseService  {

    void updateById(EsCourse esCourse);

    void insert(EsCourse esCourse);

    void deleteById(Long id);

    List<EsCourse> selectList(Object o);

    EsCourse selectById(Long id);

    PageList<EsCourse> selectListPage(EsCourseQuery query);

    void batchSave(List<EsCourse> esCourseList);

    void batchDel(List<EsCourse> esCourseList);
}
