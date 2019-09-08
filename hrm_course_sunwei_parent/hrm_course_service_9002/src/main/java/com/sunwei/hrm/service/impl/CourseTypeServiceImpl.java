package com.sunwei.hrm.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sunwei.hrm.cache.CourseTyepCache;
import com.sunwei.hrm.domain.CourseType;
import com.sunwei.hrm.mapper.CourseTypeMapper;
import com.sunwei.hrm.query.CourseTypeQuery;
import com.sunwei.hrm.service.ICourseTypeService;
import com.sunwei.hrm.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程目录 服务实现类
 * </p>
 *
 * @author sunwei
 * @since 2019-09-07
 */
@Service
public class CourseTypeServiceImpl extends ServiceImpl<CourseTypeMapper, CourseType> implements ICourseTypeService {

    @Autowired
    private CourseTypeMapper courseTypeMapper;

    @Autowired
    private CourseTyepCache courseTypeCache;
    @Override
    public PageList<CourseType> selectListPage(CourseTypeQuery query) {
        Pagination page=new Pagination(query.getPage(),query.getRows());
        List<CourseType> courseTypes= courseTypeMapper.loadListPage(page,query);
        return new PageList<>(page.getTotal(),courseTypes);
    }

    public List<CourseType> queryTypeTree(Long pid) { // -1
        List<CourseType> courseTypes = courseTypeCache.getCourseTypes();
        if (courseTypes== null || courseTypes.size()<1){
            System.out.println("db...............");
            //递归
            // return getCourseTypesRecursion(pid);
            //循环
            List<CourseType> courseTypesDb = getCourseTypesLoop(pid);
            if (courseTypesDb==null|| courseTypesDb.size()<1)
                courseTypesDb = new ArrayList<>();
            courseTypeCache.setCourseTypes(courseTypesDb);//[]
            return courseTypesDb;
        }
        System.out.println("cache...............");
        return  courseTypes;
    }

    /**
     * 方案2:循环方案:一次sql
     * @param pid 0
     * @return
     */
    public List<CourseType> getCourseTypesLoop(Long pid) { //0
        List<CourseType> result = new ArrayList<>();
        //1 查询所有类型
        List<CourseType> allTypes = courseTypeMapper.selectList(null);

        //建立id和CourseType的关联关系
        Map<Long,CourseType> allTypesDto = new HashMap<>();
        for (CourseType allType : allTypes) {
            allTypesDto.put(allType.getId(),allType);
        }
        //2 遍历判断是否是第一级  pid为传入id,
        for (CourseType type : allTypes) {
            Long pidTmp = type.getPid();
            //2.1是直接加入返回列表
            if (pidTmp.longValue()== pid.longValue()){
                result.add(type);
            }else{
                //2.2不是要把自己作为父亲儿子
                //通过pid获取父亲
                //方案1:遍历所有,通过父亲id来获取父亲
                    /*
                    for (CourseType courseType : allTypes) {
                        if(courseType.getId().longValue() == pidTmp.longValue()){
                            courseType.getChildren().add(type);
                        }

                    }*/
                //方案2:通过map获取
                CourseType parent = allTypesDto.get(pidTmp);
                //获取父亲儿子集合,把自己加进去
                parent.getChildren().add(type);
            }
        }

        return result;
    }

    /**
     * 方案1:递归,每次都要发送sql效率低下
     * @param pid
     * @return
     */
    public List<CourseType> getqueryTypeTree(Long pid) {
        //方案1:递归-自己调用自己,要有出口
        List<CourseType> children = courseTypeMapper
                .selectList(new EntityWrapper<CourseType>().eq("pid", pid));
        //要有出口
        if (children==null || children.size()<1)
        {
            return null;
        }
        for (CourseType child : children) {
            //自己调用自己
            List<CourseType> courseTypes = queryTypeTree(child.getId());
            child.setChildren(courseTypes);
        }
        return children;
    }

    @Override
    public boolean insert(CourseType entity) {

        courseTypeMapper.insert(entity);
        List<CourseType> courseTypes = queryTypeTree(0L);
        courseTypeCache.setCourseTypes(courseTypes);
        return true;
    }

    @Override
    public boolean deleteById(Serializable id) {
        courseTypeMapper.deleteById(id);
        List<CourseType> courseTypes = queryTypeTree(0L);
        courseTypeCache.setCourseTypes(courseTypes);
        return true;
    }

    @Override
    public boolean updateById(CourseType entity) {
        courseTypeMapper.updateById(entity);
        List<CourseType> courseTypes = queryTypeTree(0L);
        courseTypeCache.setCourseTypes(courseTypes);
        return true;
    }
}
