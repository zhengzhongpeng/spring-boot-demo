package com.example.springbootdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication//springboot 会自动扫描@SpringBootApplication 同级包及下级包里的bean
@MapperScan(value = "com.example.springbootdemo.dao.mapper")
public class SpringBootDemoApplication {

    //@SpringBootApplication   SpringBoot 的核心注解，是一个组合注解，
    // 包含:@Configuration 、@EnableAutoConfiguration、@ComponentScan
    //@EnableAutoConfiguration（为项目自动配置）让SpringBoot根据类路径中的jar包依赖 为 当前项目进行自动配置
    //如 添加了  spring-boot-starter-web  依赖  ，会自动添加Tomcat和SpringMvc  springBoot 会对Tomcat 和SpringMvc 进行自动配置。


    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoApplication.class, args);
    }

}
