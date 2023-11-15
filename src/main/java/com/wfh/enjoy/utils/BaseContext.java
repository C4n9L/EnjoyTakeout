package com.wfh.enjoy.utils;

/**
 * @author wfh
 * @create 2023/3/31 - 14:14
 */


/**
 * 基于ThreadLocal封装工具类，用于保存和获取当前登录用户id
 */

public class BaseContext {

    private static ThreadLocal<Long> threadLocal1 = new ThreadLocal<>();
    private static ThreadLocal<Long> threadLocal2 = new ThreadLocal<>();



    public static void setCurrentId(Long id){
        threadLocal1.set(id);
    }

    public static Long getCurrentId(){
        return threadLocal1.get();
    }

    public static void setShopId(Long id){
        threadLocal2.set(id);
    }

    public static Long getShopId(){
        return threadLocal2.get();
    }

}
