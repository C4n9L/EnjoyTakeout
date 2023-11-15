package com.wfh.enjoy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wfh.enjoy.entity.Category;
import com.wfh.enjoy.entity.Shop;
import com.wfh.enjoy.mapper.ShopMapper;
import com.wfh.enjoy.service.IShopService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author wfh
 * @create 2023/4/16 - 15:18
 */
@Service
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop> implements IShopService {

    @Override
    public Page<Shop> getShopByPage(Page<Shop> page, String name) {
        page = this.page(page,new LambdaQueryWrapper<Shop>()
                .like(StringUtils.hasText(name),Shop::getName,name)
                .orderByDesc(Shop::getCreateTime)
        );
        return page;
    }

    @Override
    public List<Shop> getShopList(String name) {
        return this.list(new LambdaQueryWrapper<Shop>()
                .like(StringUtils.hasText(name),Shop::getName,name)
                .orderByDesc(Shop::getCreateTime)
        );
    }
}
