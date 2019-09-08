package com.sunwei.hrm.service.impl;


import com.sunwei.hrm.client.doc.EsCourse;
import com.sunwei.hrm.client.query.EsCourseQuery;
import com.sunwei.hrm.repository.CourseRepository;
import com.sunwei.hrm.service.IEsCourseService;
import com.sunwei.hrm.util.PageList;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sunwei
 * @since 2019-09-05
 */
@Service
public class EsCourseServiceImpl implements IEsCourseService {
    @Autowired
    private CourseRepository repository;


    public void updateById(EsCourse esCourse) {
        repository.save(esCourse);
    }

    public void insert(EsCourse esCourse) {
        repository.save(esCourse);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public List<EsCourse> selectList(Object o) {
        Page page = (Page) repository.findAll();
        return page.getContent();
    }

    public EsCourse selectById(Long id) {
        return repository.findById(id).get();
    }

    public PageList<EsCourse> selectListPage(EsCourseQuery query) {
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        BoolQueryBuilder bool = QueryBuilders.boolQuery();
        //模糊查询 @TODO
        bool.must(QueryBuilders.matchQuery("intro", "zhang"));
        //精确过滤 @TODO
        List<QueryBuilder> filters = bool.filter();
        filters.add(QueryBuilders.rangeQuery("age").gte(0).lte(200));

        builder.withQuery(bool); //query bool must(filter)
        //排序 @TODO
        builder.withSort(SortBuilders.fieldSort("age").order(SortOrder.ASC));

        //分页 当前页从0开始
        builder.withPageable(PageRequest.of(query.getPage()-1, query.getRows()));

        //构造查询条件
        NativeSearchQuery esQuery = builder.build();
        //查询
        Page<EsCourse> page = repository.search(esQuery);
        return new PageList<>(page.getTotalElements(),page.getContent());
    }

    @Override
    public void batchSave(List<EsCourse> esCourseList) {
        repository.saveAll(esCourseList);
    }

    @Override
    public void batchDel(List<EsCourse> esCourseList) {
        repository.deleteAll(esCourseList);
    }
}
