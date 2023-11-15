package com.wfh.enjoy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wfh.enjoy.dto.DishDto;
import com.wfh.enjoy.entity.Category;
import com.wfh.enjoy.entity.Dish;
import com.wfh.enjoy.entity.DishFlavor;
import com.wfh.enjoy.mapper.DishMapper;
import com.wfh.enjoy.service.ICategoryService;
import com.wfh.enjoy.service.IDishFlavorService;
import com.wfh.enjoy.service.IDishService;
import com.wfh.enjoy.utils.BaseContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wfh
 * @create 2023/4/4 - 18:16
 */
@Slf4j
@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements IDishService {

    @Autowired
    private IDishFlavorService dishFlavorService;

    @Autowired
    private DishMapper dishMapper;

    @Autowired
    private ICategoryService categoryService;

    @Override
    @Transactional
    public void saveDishWithFlavor(DishDto dishDto) {
        //保存菜品基本信息
        this.save(dishDto);

        //将菜品id赋给口味数据
        List<DishFlavor> flavors = dishDto.getFlavors().stream().map(flavor -> {
            flavor.setDishId(dishDto.getId());
            return flavor;
        }).collect(Collectors.toList());

        //保存口味数据
        dishFlavorService.saveBatch(flavors);
    }

    @Transactional
    @Override
    public Page<DishDto> getDishDtoByPage(Page<DishDto> page,String name) {


        log.info("DishServiceImpl.shopId:{}",BaseContext.getShopId());
        page = dishMapper.getDishDtoByPage(page,name,BaseContext.getShopId());


        return page;
    }

    @Transactional
    @Override
    public DishDto getDishById(Long id) {
        Dish dish = this.getById(id);
        DishDto dishDto = new DishDto();
        BeanUtils.copyProperties(dish,dishDto);
        List<DishFlavor> flavors = dishFlavorService.list(
                    new LambdaQueryWrapper<DishFlavor>()
                            .eq(DishFlavor::getDishId, dish.getId())
                );
        dishDto.setFlavors(flavors);
        return dishDto;
    }

    @Override
    public void updateDish(DishDto dishDto) {
        //更新菜品基本信息
        this.updateById(dishDto);

        if (dishDto.getFlavors() != null) {
            //清理当前菜品的口味数据
            dishFlavorService.remove(new LambdaQueryWrapper<DishFlavor>()
                    .eq(DishFlavor::getDishId, dishDto.getId())
            );
            //添加当前所提交的口味数据
            List<DishFlavor> flavors = dishDto.getFlavors().stream().map(flavor -> {
                flavor.setDishId(dishDto.getId());
                return flavor;
            }).collect(Collectors.toList());
            dishFlavorService.saveBatch(flavors);
        }

    }

    @Override
    public List<DishDto> getDishList(Dish dish) {
        //构造查询条件
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(dish.getCategoryId() != null ,Dish::getCategoryId,dish.getCategoryId());
        //添加条件，查询状态为1（起售状态）的菜品
        queryWrapper.eq(Dish::getStatus,1);

        //添加排序条件
        queryWrapper.orderByAsc(Dish::getSort).orderByDesc(Dish::getUpdateTime);

        List<Dish> list = this.list(queryWrapper);

        List<DishDto> dishDtoList = list.stream().map((item) -> {
            DishDto dishDto = new DishDto();

            BeanUtils.copyProperties(item,dishDto);

            Long categoryId = item.getCategoryId();//分类id
            //根据id查询分类对象
            Category category = categoryService.getById(categoryId);

            if(category != null){
                String categoryName = category.getName();
                dishDto.setCategoryName(categoryName);
            }

            //当前菜品的id
            Long dishId = item.getId();
            LambdaQueryWrapper<DishFlavor> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(DishFlavor::getDishId,dishId);
            //SQL:select * from dish_flavor where dish_id = ?
            List<DishFlavor> dishFlavorList = dishFlavorService.list(lambdaQueryWrapper);
            dishDto.setFlavors(dishFlavorList);
            return dishDto;
        }).collect(Collectors.toList());
        return dishDtoList;
    }

    @Override
    public void updateDishStatus(int status, String ids) {
        dishMapper.updateDishStatus(status,ids);

    }
}
