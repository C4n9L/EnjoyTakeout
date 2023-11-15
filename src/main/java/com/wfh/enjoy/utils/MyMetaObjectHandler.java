package com.wfh.enjoy.utils;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.wfh.enjoy.entity.Employee;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

/**
 * @author wfh
 * @create 2023/3/31 - 13:23
 */
@Slf4j
@Configuration
public class MyMetaObjectHandler implements MetaObjectHandler {


    /**
     * 插入自动填充
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {

        //由于管理员没有正常店铺id，所以要进行判断
        Long shopId = (Long) getFieldValByName("shopId", metaObject);
        log.info("baseId:" + BaseContext.getShopId());
        log.info("shopId:" + shopId);

        if(shopId == null && BaseContext.getShopId() != null){
                metaObject.setValue("shopId", BaseContext.getShopId());
        }

        metaObject.setValue("createTime", LocalDateTime.now());
        metaObject.setValue("updateTime", LocalDateTime.now());

        metaObject.setValue("createUser", BaseContext.getCurrentId());
        metaObject.setValue("updateUser", BaseContext.getCurrentId());

    }

    /**
     * 更新自动填充
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {

        metaObject.setValue("updateTime", LocalDateTime.now());
        log.info("更新人:{}",BaseContext.getCurrentId());
        metaObject.setValue("updateUser", BaseContext.getCurrentId());
    }
}
