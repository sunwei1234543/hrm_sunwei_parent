package com.sunwei.hrm.client;


import com.sunwei.hrm.doc.EsCourse;
import com.sunwei.hrm.query.EsCourseQuery;
import com.sunwei.hrm.util.AjaxResult;
import com.sunwei.hrm.util.PageList;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author yaohuaipeng
 * @date 2018/10/8-16:18
 */
@Component
public class EsCourseClientHystrixFallbackFactory implements FallbackFactory<EsCourseClient> {

    @Override
    public EsCourseClient create(Throwable throwable) {
        return new EsCourseClient() {
            @Override
            public AjaxResult save(EsCourse esCourse) {
                return null;
            }

            @Override
            public AjaxResult delete(Integer id) {
                return null;
            }

            @Override
            public EsCourse get(Long id) {
                return null;
            }

            @Override
            public List<EsCourse> list() {
                return null;
            }

            @Override
            public PageList<EsCourse> json(EsCourseQuery query) {
                return null;
            }

            @Override
            public AjaxResult batchSave(List<EsCourse> esCoursesList) {
                return null;
            }

            @Override
            public void batchDel(List<EsCourse> esCoursesList) {

            }
        };
    }
}
