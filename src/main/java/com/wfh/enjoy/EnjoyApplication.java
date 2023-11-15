package com.wfh.enjoy;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author wfh
 * @create 2023/3/10 - 20:14
 */
@Slf4j
@SpringBootApplication
@MapperScan("com.wfh.enjoy.mapper")
@EnableTransactionManagement
public class EnjoyApplication {
    public static void main(String[] args) {
        SpringApplication.run(EnjoyApplication.class,args);
    }
}
