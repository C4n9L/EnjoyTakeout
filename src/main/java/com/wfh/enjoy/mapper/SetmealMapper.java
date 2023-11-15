package com.wfh.enjoy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wfh.enjoy.dto.SetmealDto;
import com.wfh.enjoy.entity.Setmeal;
import org.springframework.stereotype.Repository;

/**
 * @author wfh
 * @create 2023/4/4 - 17:02
 */
@Repository
public interface SetmealMapper extends BaseMapper<Setmeal> {
    Page<SetmealDto> getSetmealDtoByPage(Page<SetmealDto> page, String name, Long shopId);

    SetmealDto getSetmealById(Long id);
}
