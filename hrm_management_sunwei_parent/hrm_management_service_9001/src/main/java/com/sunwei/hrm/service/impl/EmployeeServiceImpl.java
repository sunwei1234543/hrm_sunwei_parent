package com.sunwei.hrm.service.impl;

import com.sunwei.hrm.domain.Employee;
import com.sunwei.hrm.mapper.EmployeeMapper;
import com.sunwei.hrm.service.IEmployeeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sunwei
 * @since 2019-09-07
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

}
