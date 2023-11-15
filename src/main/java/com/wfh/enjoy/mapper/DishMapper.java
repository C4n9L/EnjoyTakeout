package com.wfh.enjoy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wfh.enjoy.dto.CategoryDto;
import com.wfh.enjoy.dto.DishDto;
import com.wfh.enjoy.entity.Dish;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

/**
 * @author wfh
 * @create 2023/4/4 - 17:02
 */
@Repository
public interface DishMapper extends BaseMapper<Dish> {

    Page<DishDto> getDishDtoByPage(Page<DishDto> page,String name,Long shopId);

    void updateDishStatus(int status, String ids);
}
