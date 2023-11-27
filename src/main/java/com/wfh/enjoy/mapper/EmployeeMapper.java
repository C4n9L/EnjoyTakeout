package com.wfh.enjoy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wfh.enjoy.entity.Employee;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author wfh
 * @create 2023/3/21 - 15:16
 */
@Repository
public interface EmployeeMapper extends BaseMapper<Employee> {
    //Page<Employee> getEmployeeByPage(Page<Employee> page, String name, Long shopId);
}
