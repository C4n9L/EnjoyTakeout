package com.wfh.enjoy.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wfh.enjoy.dto.DishDto;
import com.wfh.enjoy.entity.Category;
import com.wfh.enjoy.entity.Dish;
import com.wfh.enjoy.entity.Employee;
import com.wfh.enjoy.service.ICategoryService;
import com.wfh.enjoy.service.IDishFlavorService;
import com.wfh.enjoy.service.IDishService;
import com.wfh.enjoy.utils.FileUtils;
import com.wfh.enjoy.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author wfh
 * @create 2023/4/5 - 15:46
 */
@Slf4j
@RestController
@RequestMapping("/dish")
public class DishController {

    @Autowired
    private IDishService dishService;

    @Autowired
    private IDishFlavorService dishFlavorService;

    /**
     * 新增菜品信息
     * @param dishDto
     * @return
     */
    @PostMapping
    public Result<String> saveDish(@RequestBody DishDto dishDto){
        dishService.saveDishWithFlavor(dishDto);
        return Result.ok("新增菜品成功");
    }

    /**
     * 修改菜品信息
     * @param dishDto
     * @return
     */
    @PutMapping
    public Result<String> updateDish(@RequestBody DishDto dishDto){

        dishService.updateDish(dishDto);

        return Result.ok("修改菜品成功");
    }

    /**
     * 分页展示菜品
     * @param page
     * @param current
     * @param size
     * @param name
     * @return
     */
    @RequestMapping("/page")
    public Result<Page<DishDto>> showDishByPage(
            Page<DishDto> page,
            @RequestParam("page") Integer current,
            @RequestParam("pageSize") Integer size,
            @RequestParam(value = "name",required = false) String name
    ){
        page.setCurrent(current);
        page.setSize(size);

        page = dishService.getDishDtoByPage(page,name);

        return Result.ok(page);
    }

    /**
     * 根据id查询菜品信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<DishDto> getDishById(@PathVariable Long id){
        DishDto dishDto = dishService.getDishById(id);
        return Result.ok(dishDto);
    }

    /**
     * 根据条件查询对应菜品数据
     * @param dish
     * @return
     */
    @GetMapping("/list")
    public Result<List<DishDto>> getDishList(Dish dish){

        List<DishDto> dishes =dishService.getDishList(dish);

        return Result.ok(dishes);
    }

    @PutMapping("/status/{status}")
    public Result<String> updateDishStatus(@PathVariable int status,@RequestParam("ids") String ids) {

        dishService.updateDishStatus(status,ids);

        return Result.ok("修改成功");
    }
}
