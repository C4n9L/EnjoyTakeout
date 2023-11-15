package com.wfh.enjoy.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 员工
 * @author wfh
 * @create 2023/3/21 - 15:15
 */
@Data
@TableName("employee")
public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;


    private Long id;

    @TableField(fill = FieldFill.INSERT)
    private Long shopId;//店铺id

    @TableField(exist = false)
    private String shopName;//店铺名称

    private String username;//账号

    private String name;//姓名

    private String password;//密码

    private String phone;//电话号码

    private String sex;//性别

    private String idNumber;//身份证号码

    private Integer status;//封禁状态

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;//创建时间

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;//更新时间

    @TableField(fill = FieldFill.INSERT)
    private Long createUser;//创建者

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;//修改者
}
