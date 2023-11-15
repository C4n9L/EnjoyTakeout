package com.wfh.enjoy.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author wfh
 * @create 2023/4/16 - 15:15
 */
@Data
@TableName("shop")
public class Shop {

    private static final long serialVersionUID = 1L;

    private Long id;

    //店铺名称
    private String name;

    //店铺id
    @TableField(exist = false)
    private Long shopId;

    //店铺图标
    private String image;

    //商家电话
    private String phone;

    //省市区数据
    private String provinceCode;

    private String provinceName;

    private String cityCode;

    private String cityName;

    private String districtCode;

    private String districtName;


    //店铺具体地址
    private String detail;

    //店铺描述
    private String description;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;


    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


    @TableField(fill = FieldFill.INSERT)
    private Long createUser;


    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;
}
