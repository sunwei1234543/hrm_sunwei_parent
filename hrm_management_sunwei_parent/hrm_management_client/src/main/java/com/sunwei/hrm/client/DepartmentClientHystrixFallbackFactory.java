package com.sunwei.hrm.client;

import com.sunwei.hrm.domain.Department;
import com.sunwei.hrm.query.DepartmentQuery;
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
public class DepartmentClientHystrixFallbackFactory implements FallbackFactory<DepartmentClient> {

    @Override
    public DepartmentClient create(Throwable throwable) {
        return new DepartmentClient() {
            @Override
            public AjaxResult save(Department department) {
                return null;
            }

            @Override
            public AjaxResult delete(Integer id) {
                return null;
            }

            @Override
            public Department get(Long id) {
                return null;
            }

            @Override
            public List<Department> list() {
                return null;
            }

            @Override
            public PageList<Department> json(DepartmentQuery query) {
                return null;
            }
        };
    }
}
