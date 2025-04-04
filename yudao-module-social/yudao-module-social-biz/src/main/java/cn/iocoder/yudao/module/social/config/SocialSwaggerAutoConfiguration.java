package cn.iocoder.yudao.module.social.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * social 模块的 Swagger 配置
 */
@Configuration(proxyBeanMethods = false)
public class SocialSwaggerAutoConfiguration {

    /**
     * social 模块的 API 分组
     */
    @Bean
    public GroupedOpenApi socialGroupedOpenApi() {
        return GroupedOpenApi.builder()
                .group("social")
                .packagesToScan("cn.iocoder.yudao.module.social.controller")
                .build();
    }

} 