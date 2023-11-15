package com.wfh.enjoy.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wfh.enjoy.entity.Shop;

import java.util.List;

/**
 * @author wfh
 * @create 2023/4/16 - 15:18
 */
public interface IShopService extends IService<Shop> {
    /**
     * 店铺分类
     * @param page
     * @param name
     * @return
     */
    Page<Shop> getShopByPage(Page<Shop> page, String name);

    /**
     * 获取店铺列表
     * @return
     */
    List<Shop> getShopList(String name);
}
