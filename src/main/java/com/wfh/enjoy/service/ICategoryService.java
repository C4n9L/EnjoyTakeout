package com.wfh.enjoy.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wfh.enjoy.dto.CategoryDto;
import com.wfh.enjoy.entity.Category;

import java.util.List;

/**
 * @author wfh
 * @create 2023/3/31 - 15:15
 */
public interface ICategoryService extends IService<Category> {

    /**
     * 删除分类
     * @param id
     */
    void deleteCategoryById(Long id);

    /**
     * 保存分类信息
     * @param category
     */
    void saveCategory(Category category);

    /**
     * 获取分类列表
     * @param category
     * @return
     */
    List<Category> getCategoryList(Category category);

    /**
     * 分页显示分类
     * @param page
     * @return
     */
    Page<CategoryDto> getCategoryByPage(Page<CategoryDto> page);
}
