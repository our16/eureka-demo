package org.example;

/**
 * Hello world!
 *
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// 开启 EurekaClient 注解，目前版本如果配置了 Eureka 注册中心，默认会开启该注解
//@EnableEurekaClient
public class ServiceProvider02Application {

    public static void main(String[] args) {
        SpringApplication.run(ServiceProvider02Application.class, args);
    }

}

