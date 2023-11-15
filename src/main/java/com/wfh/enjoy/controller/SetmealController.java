package com.wfh.enjoy.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wfh.enjoy.dto.DishDto;
import com.wfh.enjoy.dto.SetmealDto;
import com.wfh.enjoy.entity.Category;
import com.wfh.enjoy.entity.Setmeal;
import com.wfh.enjoy.service.ISetmealService;
import com.wfh.enjoy.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author wfh
 * @create 2023/4/6 - 19:28
 */
@Slf4j
@RestController
@RequestMapping("/setmeal")
public class SetmealController {

    @Autowired
    private ISetmealService setmealService;

    @PostMapping
    public Result<String> saveSetmeal(@RequestBody SetmealDto setmealDto){

        log.info(JSON.toJSONString(setmealDto));
        setmealService.saveSetmeal(setmealDto);

        return Result.ok("保存套餐成功");
    }


    @PutMapping
    public Result<String> updateSetmeal(@RequestBody SetmealDto setmealDto){
        log.info(JSON.toJSONString(setmealDto));
        setmealService.saveSetmeal(setmealDto);

        return Result.ok("修改套餐成功");
    }
    /**
     * 根据id查询套餐信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<SetmealDto> getSetmealById(@PathVariable Long id){

        SetmealDto setmealDto = setmealService.getSetmealById(id);

        return Result.ok(setmealDto);
    }

    @GetMapping("/page")
    public Result<Page<SetmealDto>> showSetmealByPage(
            Page<SetmealDto> page,
            @RequestParam("page") Integer current,
            @RequestParam("pageSize") Integer size,
            @RequestParam(value = "name",required = false) String name
    ){
        page.setCurrent(current);
        page.setSize(size);

        page = setmealService.getSetmealDtoPage(page,name);

        return Result.ok(page);
    }

    /**
     * 删除套餐
     * @param ids
     * @return
     */
    @DeleteMapping
    public Result<String> deleteSetmeal(@RequestParam List<Long> ids){
        setmealService.removeSetmeal(ids);
        return Result.ok("删除套餐成功");
    }

    /**
     * 根据条件查询套餐数据
     * @param setmeal
     * @return
     */
    @GetMapping("/list")
    public Result<List<Setmeal>> list(Setmeal setmeal){
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(setmeal.getCategoryId() != null,Setmeal::getCategoryId,setmeal.getCategoryId());
        queryWrapper.eq(setmeal.getStatus() != null,Setmeal::getStatus,setmeal.getStatus());
        queryWrapper.orderByDesc(Setmeal::getUpdateTime);

        List<Setmeal> list = setmealService.list(queryWrapper);

        return Result.ok(list);
    }
}
