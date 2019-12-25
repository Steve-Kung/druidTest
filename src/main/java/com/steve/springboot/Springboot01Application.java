package com.steve.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@ServletComponentScan // 使用该注解后，Servlet/Filter/Listener都可以直接通过@WebServlet、@WebFilter、@WebListener注解自动注册
@ImportResource(locations = {"classpath:spring-mvc.xml"}) // 导入资源配置文件，让SpringBoot可以读取到，相当于xml文件的import标签
@EnableAsync
@EnableRetry
public class Springboot01Application {

    public static void main(String[] args) {
        SpringApplication.run(Springboot01Application.class, args);
    }

}

// 此项目是参考一步一步学spring-boot2微服务项目实战