package com.sunwei.hrm.repository;

import com.sunwei.hrm.client.doc.EsCourse;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CourseRepository extends ElasticsearchRepository<EsCourse,Long>{

}
