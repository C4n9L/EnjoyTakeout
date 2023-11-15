package com.wfh.enjoy.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wfh.enjoy.entity.Shop;
import com.wfh.enjoy.service.IShopService;
import com.wfh.enjoy.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author wfh
 * @create 2023/4/16 - 15:20
 */
@Slf4j
@RestController
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private IShopService shopService;

    /**
     * 分页查询店铺
     * @param page
     * @param current
     * @param size
     * @param name
     * @return
     */
    @GetMapping("/page")
    public Result<Page<Shop>> getShopPage(
            Page<Shop> page,
            @RequestParam("page") Integer current,
            @RequestParam("pageSize") Integer size,
            @RequestParam(value = "name",required = false) String name
    ){
        page.setCurrent(current);
        page.setSize(size);

        page = shopService.getShopByPage(page,name);

        return Result.ok(page);
    }


    /**
     * 保存店铺
     * @param shop
     * @return
     */
    @PostMapping
    public Result<String> saveShop(@RequestBody Shop shop){

        shopService.save(shop);

        return Result.ok("保存店铺成功");
    }

    /**
     * 保存店铺
     * @param shop
     * @return
     */
    @PutMapping
    public Result<String> updateShop(@RequestBody Shop shop){

        shopService.updateById(shop);

        return Result.ok("保存店铺成功");
    }

    /**
     * 根据id查询店铺
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<Shop> getShopById(@PathVariable Long id){

        log.info("id:{}",id);

        Shop shop = shopService.getById(id);

        return Result.ok(shop);
    }

    /**
     * 获取店铺列表
     * @return
     */
    @GetMapping("/list")
    public Result<List<Shop>> getShopList(@RequestParam(value = "name",required = false) String name){

        List<Shop> shopList = shopService.getShopList(name);

        log.info("获取到shopList:{}",shopList);

        return Result.ok(shopList);
    }

}
