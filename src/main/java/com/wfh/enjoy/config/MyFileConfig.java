package com.wfh.enjoy.config;


import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wfh
 * @create 2023/3/30 - 15:53
 */
@Configuration
public class    MyFileConfig {

    @Bean
    public UploadManager uploadManager(){
        //构造一个带指定 Region 对象的配置类
        com.qiniu.storage.Configuration cfg = new com.qiniu.storage.Configuration(Region.region2());
        //...其他参数参考类注释
        return new UploadManager(cfg);
    }
}
