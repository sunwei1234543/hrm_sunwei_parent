package com.sunwei.hrm.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.sunwei.hrm.domain.Employee;
import com.sunwei.hrm.domain.Tenant;
import com.sunwei.hrm.mapper.EmployeeMapper;
import com.sunwei.hrm.mapper.TenantMapper;
import com.sunwei.hrm.service.ITenantService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sunwei
 * @since 2019-09-07
 */
@Service
public class TenantServiceImpl extends ServiceImpl<TenantMapper, Tenant> implements ITenantService {

    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private TenantMapper tenantMapper;

    @Override
    public boolean insert(Tenant tenant) {
        tenant.setRegisterTime(new Date());
        tenant.setState(false);
        //添加机构
        tenantMapper.insert(tenant);
        //添加管理员
        Employee adminUser = tenant.getAdminUser();
        adminUser.setInputTime(new Date());//输入时间
        adminUser.setState(1);//状态正常
        adminUser.setType(true);//是否是租户管理员
        adminUser.setTenantId(tenant.getId());//所属租户
        employeeMapper.insert(adminUser);
        //添加套餐中间表
        tenantMapper.saveTenantMeals(tenant.getmealsMap());

        return true;
    }

    @Override
    public boolean deleteById(Serializable id) {
        //删除机构（租户）
        tenantMapper.deleteById(id);
        //删除管理员
        Wrapper<Employee> wrapper = new EntityWrapper<>();
        wrapper.eq("tenant_id",id);
        employeeMapper.delete(wrapper);
        //删除中间表
        tenantMapper.removeTenantMeal(id);
        return true;
    }

    @Override
    public boolean updateById(Tenant entity) {
        //修改机构
        tenantMapper.updateById(entity);
        //修改管理员
        employeeMapper.updateById(entity.getAdminUser());
        //修改中间表  先删除后添加
        tenantMapper.removeTenantMeal(entity.getId());
        tenantMapper.saveTenantMeals(entity.getmealsMap());
        return true;
    }
}
