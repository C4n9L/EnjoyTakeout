package com.wfh.enjoy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wfh.enjoy.dto.CategoryDto;
import com.wfh.enjoy.entity.Category;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wfh
 * @create 2023/3/31 - 15:13
 */
@Repository
public interface CategoryMapper extends BaseMapper<Category> {

    Page<CategoryDto> getCategoryByPage(Page<CategoryDto> page,Long shopId);

    List<Category> getCategoryList(Integer type,Long shopId);
}
