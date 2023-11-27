package com.wfh.enjoy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wfh.enjoy.dto.CategoryDto;
import com.wfh.enjoy.entity.Category;
import com.wfh.enjoy.entity.Dish;
import com.wfh.enjoy.entity.Setmeal;
import com.wfh.enjoy.exception.EnjoyTakeoutException;
import com.wfh.enjoy.mapper.CategoryMapper;
import com.wfh.enjoy.mapper.DishMapper;
import com.wfh.enjoy.service.ICategoryService;
import com.wfh.enjoy.service.IDishService;
import com.wfh.enjoy.service.ISetmealService;
import com.wfh.enjoy.utils.BaseContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

/**
 * @author wfh
 * @create 2023/3/31 - 15:15
 */
@Slf4j
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private IDishService dishService;

    @Autowired
    private ISetmealService setmealService;

    /**
     * 根据id删除分类，但删除之前先进行判断
     * @param id
     */
    @Override
    public void deleteCategoryById(Long id) {

        //查询当前分类是否关联了菜品，如果己经关联，抛出一个业务异常
        int dishCount = dishService.count(
                new LambdaQueryWrapper<Dish>()
                        .eq(Dish::getCategoryId, id)
        );
        if(dishCount > 0){
            //已经关联，抛出异常
            throw new EnjoyTakeoutException("当前分类下存在菜品，不能删除");
        }

        //查询当前分类是否关联了套餐，如果已经关联，抛出一个业务异常
        int setmealCount = setmealService.count(
                new LambdaQueryWrapper<Setmeal>()
                        .eq(Setmeal::getCategoryId, id)
        );
        if(setmealCount > 0){
            //已经关联，抛出异常
            throw new EnjoyTakeoutException("当前分类下存在套餐，不能删除");
        }
        //正常删除
        super.removeById(id);
    }

    @Override
    public void saveCategory(Category category) {

        if(category.getShopId() != null){//不为null代表是管理员添加
            this.save(category);
        }
        else {//否则将从线程中拿值
            category.setShopId(BaseContext.getShopId());
            this.save(category);
        }

    }

    @Override
    public List<Category> getCategoryList(Category category) {
        Long shopId = category.getShopId();
        Integer type = category.getType();


        boolean flag = true;

        if(shopId == null || (shopId == null && type == 1)){
           shopId = BaseContext.getShopId();
           log.info("CategoryServiceImpl.shopId:{}",shopId);
           flag = false;
        }

        List<Category> categoryList = this.list(new LambdaQueryWrapper<Category>()
                        .eq(Category::getType,type)
                        .eq(Category::getShopId,shopId)
                );

        return categoryList;
    }

    @Override
    public Page<CategoryDto> getCategoryByPage(Page<CategoryDto> page) {

        page = categoryMapper.getCategoryByPage(page,BaseContext.getShopId());

        return page;
    }


}
