package com.wfh.enjoy.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wfh.enjoy.entity.Employee;

/**
 * @author wfh
 * @create 2023/3/21 - 15:17
 */
public interface IEmployeeService extends IService<Employee> {

    /**
     * 根据条件分页查询员工信息
     * @param page
     * @param name
     * @param shopId
     * @return
     */
    Page<Employee> getEmployeeByPage(Page<Employee> page, String name, Long shopId);

}
