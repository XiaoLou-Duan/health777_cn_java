package cn.iocoder.yudao.module.medical.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * medical 模块的 Swagger 配置
 */
@Configuration(proxyBeanMethods = false)
public class MedicalSwaggerAutoConfiguration {

    /**
     * medical 模块的 API 分组
     */
    @Bean
    public GroupedOpenApi medicalGroupedOpenApi() {
        return GroupedOpenApi.builder()
                .group("medical")
                .packagesToScan("cn.iocoder.yudao.module.medical.controller")
                .build();
    }

} 