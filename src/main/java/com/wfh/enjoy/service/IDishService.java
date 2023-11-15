package com.wfh.enjoy.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wfh.enjoy.dto.DishDto;
import com.wfh.enjoy.entity.Dish;

import java.util.List;

/**
 * @author wfh
 * @create 2023/4/4 - 17:04
 */
public interface IDishService extends IService<Dish> {

    /**
     * 新增菜品，同时添加口味数据
     * @param dishDto
     */
    void saveDishWithFlavor(DishDto dishDto);

    /**
     * 获取菜品分页信息
     */
    Page<DishDto> getDishDtoByPage(Page<DishDto> page,String name);

    /**
     * 根据id获取菜品信息
     * @param id
     * @return
     */
    DishDto getDishById(Long id);

    /**
     * 更新菜品信息，同时更新口味数据
     * @param dishDto
     */
    void updateDish(DishDto dishDto);

    List<DishDto> getDishList(Dish dish);

    void updateDishStatus(int status, String id);
}
