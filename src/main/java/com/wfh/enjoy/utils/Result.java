package com.wfh.enjoy.utils;


import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 通用返回结果，服务端响应的数据会封装成此对象
 * @param <T>
 */
@Data
public class Result<T>{

    private Integer code; //状态码

    private String msg; //错误信息

    private T data; //数据

    private Map map = new HashMap<>(); //动态数据

    //成功
    public static <T> Result<T> ok(T obj){
        Result<T> result = new Result<>();
        result.data = obj;
        result.code = 200;
        return result;
    }

    //失败
    public static <T> Result<T> error(String msg){
        Result<T> result = new Result<>();
        result.msg = msg;
        result.code = 400;
        return result;
    }

    public Result<T> add(String key, Object value){
        this.map.put(key,value);
        return this;
    }
}
