package com.sdau;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Slf4j                               //输出日志log
@SpringBootApplication
@MapperScan({"com.sdau.mapper"})
@ServletComponentScan                //扫描webfilter注解
@EnableTransactionManagement         //开启事务注解支持
@EnableCaching                       //开启缓存注解功能
public class PhoneSystemApplication {

    public static void main(String[] args) {

        SpringApplication.run(PhoneSystemApplication.class, args);
         log.info("项目启动成功........");
    }

}
