package com.wfh.enjoy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wfh.enjoy.entity.Employee;
import com.wfh.enjoy.mapper.EmployeeMapper;
import com.wfh.enjoy.service.IEmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

/**
 * @author wfh
 * @create 2023/3/21 - 15:17
 */
@Slf4j
@Transactional
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public Page<Employee> getEmployeeByPage(Page<Employee> page, String name, Long shopId) {

        log.info("emp.shopId:{}",shopId);

        page = employeeMapper.getEmployeeByPage(page,name,shopId);

        return page;
    }
}
