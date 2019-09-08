package com.sunwei.hrm.mapper;

import com.sunwei.hrm.domain.Tenant;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author sunwei
 * @since 2019-09-07
 */
public interface TenantMapper extends BaseMapper<Tenant> {

    /**
     * 保存中间表
     * @param getmealsMap
     */
    void saveTenantMeals(List<Map<String, Long>> getmealsMap);

    /**
     * 删除中间表
     * @param id
     */
    void removeTenantMeal(Serializable id);
}
