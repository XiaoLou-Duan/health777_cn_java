package cn.iocoder.yudao.module.nutrition.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * nutrition 模块的 Swagger 配置
 */
@Configuration(proxyBeanMethods = false)
public class NutritionSwaggerAutoConfiguration {

    /**
     * nutrition 模块的 API 分组
     */
    @Bean
    public GroupedOpenApi nutritionGroupedOpenApi() {
        return GroupedOpenApi.builder()
                .group("nutrition")
                .packagesToScan("cn.iocoder.yudao.module.nutrition.controller")
                .build();
    }

} 