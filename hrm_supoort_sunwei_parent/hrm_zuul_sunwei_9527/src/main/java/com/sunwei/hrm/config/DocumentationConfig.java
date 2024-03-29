package com.sunwei.hrm.config;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

@Component
@Primary
public class DocumentationConfig implements SwaggerResourcesProvider {
    @Override
    public List<SwaggerResource> get() {
        List resources = new ArrayList<>();
        resources.add(swaggerResource("系统管理", "/services/sysmanage/v2/api-docs", "2.0"));
        resources.add(swaggerResource("课程管理", "/services/course/v2/api-docs", "2.0"));
        resources.add(swaggerResource("分布式文件系统管理", "/services/fastdfs/v2/api-docs", "2.0"));
        resources.add(swaggerResource("分布式全文检索系统管理", "/services/es/v2/api-docs", "2.0"));
        resources.add(swaggerResource("缓存redis系统管理", "/services/redis/v2/api-docs", "2.0"));
        resources.add(swaggerResource("静态资源系统管理", "/services/page/v2/api-docs", "2.0"));
        resources.add(swaggerResource("页面队列管理", "/services/rabbitmq/v2/api-docs", "2.0"));
        return resources;

    }

    private SwaggerResource swaggerResource(String name, String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }
}