package org.example.config;

import org.example.filter.CustomGatewayFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 网关路由配置类
 */
@Configuration
public class GatewayRoutesConfiguration {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        System.out.println("自定义网关过滤器注册");
        return builder.routes().route(r -> r
                        // 断言（判断条件）
                        .path("/product/**")
                        // 目标 URI，路由到微服务的地址
                        .uri("lb://service-provider")
                        // 注册自定义网关过滤器
                        .filters(new CustomGatewayFilter())
                        // 路由 ID，唯一
                        .id("service-provider"))
                .build();
    }

}
