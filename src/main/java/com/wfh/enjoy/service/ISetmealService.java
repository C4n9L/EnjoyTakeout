package com.wfh.enjoy.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wfh.enjoy.dto.SetmealDto;
import com.wfh.enjoy.entity.Setmeal;

import java.util.List;

/**
 * @author wfh
 * @create 2023/4/4 - 17:04
 */
public interface ISetmealService extends IService<Setmeal> {
    /**
     * 保存套餐信息,同时保存套餐和菜品的关系
     * @param setmealDto
     */
    void saveSetmeal(SetmealDto setmealDto);

    /**
     * 套餐分页信息
     * @param page
     * @param name
     * @return
     */
    Page<SetmealDto> getSetmealDtoPage(Page<SetmealDto> page, String name);

    /**
     * 删除套餐，同时删除套餐和菜品的关系
     * @param ids
     */
    void removeSetmeal(List<Long> ids);

    SetmealDto getSetmealById(Long id);
}
