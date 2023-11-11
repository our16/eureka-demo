package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
// 开启 EurekaClient 注解，目前版本如果配置了 Eureka 注册中心，默认会开启该注解
//@EnableEurekaClient
// 开启 FeignClients 注解
@EnableFeignClients
public class ServiceConsumerFeignApplication
{
    public static void main(String[] args) {
        SpringApplication.run(ServiceConsumerFeignApplication.class, args);
    }
}
