package com.wfh.enjoy.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wfh.enjoy.dto.CategoryDto;
import com.wfh.enjoy.entity.Category;
import com.wfh.enjoy.service.ICategoryService;
import com.wfh.enjoy.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author wfh
 * @create 2023/3/31 - 14:32
 */
@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;


    /**
     * 新增分类
     * @param category
     * @return
     */
    @PostMapping
    public Result<String> saveCategory(@RequestBody Category category){

        log.info("店铺id是:{}",category.getShopId());
        // 保存分类信息
        categoryService.saveCategory(category);

        return Result.ok("新增分类成功");
    }


    /**
     * 分页展示分类信息
     * @param page
     * @param current
     * @param size
     * @return
     */
    @GetMapping("/page")
    public Result<Page<CategoryDto>> showCategoryByPage(
            Page<CategoryDto> page,
            @RequestParam("page") Integer current,
            @RequestParam("pageSize") Integer size
    ){
        page.setCurrent(current);
        page.setSize(size);

        Page<CategoryDto> categoryDtoPage = categoryService.getCategoryByPage(page);

        return Result.ok(categoryDtoPage);
    }

    /**
     * 根据id删除分类
     * @param id
     * @return
     */
    @DeleteMapping
    public Result<String> deleteCategory(Long id){

//        categoryService.removeById(id);
        categoryService.deleteCategoryById(id);

        return Result.ok("分类删除成功");
    }

    /**
     * 根据id修改分类信息
     * @param category
     * @return
     */
    @PutMapping
    public Result<String> editCategory(@RequestBody Category category){


        log.info("店铺ID是:{}",category.getShopId());
        categoryService.updateById(category);

        return Result.ok("分类修改成功");
    }

    /**
     * 根据条件查询分类数据
     * @param category
     * @return
     */
    @GetMapping("/list")
    public Result<List<Category>> getCategoryList(Category category){

        List<Category> categoryList = categoryService.getCategoryList(category);
        log.info("获取到categoryList:{}",categoryList);
        return Result.ok(categoryList);
    }
}
