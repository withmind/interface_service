package com.bootdo;

import com.bootdo.soapAPI.service.IUserService;
import com.bootdo.soapAPI.service.impl.UserServiceImpl;

import javax.xml.ws.Endpoint;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableAutoConfiguration(exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
})
@EnableTransactionManagement
@ServletComponentScan
@MapperScan("com.bootdo.*.dao")
@SpringBootApplication
@EnableCaching
public class BootdoApplication {

    @Autowired
    private static ConfigBeanProp configBeanProp;

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(BootdoApplication.class, args);

        // 1.服务实现类对象
        IUserService userService = new UserServiceImpl();
        // 2.发布服务地址
        String address = ctx.getEnvironment().getProperty("messagesService.url");
        // 3.发布服务
        Endpoint.publish(address,userService);
        System.out.println("Message服务开启了....");
        System.out.println( configBeanProp.getEmpiurl());

    }
}
