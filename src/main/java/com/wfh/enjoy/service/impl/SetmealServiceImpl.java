package com.wfh.enjoy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wfh.enjoy.dto.SetmealDto;
import com.wfh.enjoy.entity.Category;
import com.wfh.enjoy.entity.Dish;
import com.wfh.enjoy.entity.Setmeal;
import com.wfh.enjoy.entity.SetmealDish;
import com.wfh.enjoy.exception.EnjoyTakeoutException;
import com.wfh.enjoy.mapper.SetmealMapper;
import com.wfh.enjoy.service.ICategoryService;
import com.wfh.enjoy.service.ISetmealDishService;
import com.wfh.enjoy.service.ISetmealService;
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
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements ISetmealService {

    @Autowired
    private ISetmealDishService setmealDishService;

    @Autowired
    private SetmealMapper setmealMapper;



    @Override
    public void saveSetmeal(SetmealDto setmealDto) {

        if(setmealDto.getShopId() == null || setmealDto.getShopId() == 1){
           setmealDto.setShopId(BaseContext.getShopId());
        }

        //保存套餐的基本信息
        this.save(setmealDto);

        List<SetmealDish> dishes = setmealDto
                .getSetmealDishes()
                .stream()
                .map(dish -> {
                    dish.setShopId(setmealDto.getShopId());
                    dish.setSetmealId(setmealDto.getId());
                    return dish;
                }).collect(Collectors.toList());

        //保存套餐和菜品的关联信息
        setmealDishService.saveBatch(dishes);

    }

    @Transactional
    @Override
    public Page<SetmealDto> getSetmealDtoPage(Page<SetmealDto> page, String name) {
        page = setmealMapper.getSetmealDtoByPage(page,name,BaseContext.getShopId());

        return page;
    }

    @Override
    public void removeSetmeal(List<Long> ids) {
        //查询套餐状态
        int count = this.count(new LambdaQueryWrapper<Setmeal>()
                .in(Setmeal::getId, ids)
                .eq(Setmeal::getStatus, 1)
        );
        if(count > 0){
            //如果count大于0说明其中有套餐正在售卖，抛出异常
            throw new EnjoyTakeoutException("套餐正在售卖中,不能删除");
        }

        //删除套餐
        this.removeByIds(ids);

        //删除套餐与菜品关联数据
        setmealDishService.remove(new LambdaQueryWrapper<SetmealDish>()
                .in(SetmealDish::getSetmealId,ids)
        );
    }

    @Override
    public SetmealDto getSetmealById(Long id) {

        SetmealDto setmealDto = setmealMapper.getSetmealById(id);
        log.info("获取到套餐信息:{}",setmealDto);
        return setmealDto;
    }
}
