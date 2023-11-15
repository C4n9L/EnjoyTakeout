package com.wfh.enjoy.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wfh.enjoy.entity.Employee;
import com.wfh.enjoy.entity.Shop;
import com.wfh.enjoy.service.IEmployeeService;
import com.wfh.enjoy.utils.BaseContext;
import com.wfh.enjoy.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author wfh
 * @create 2023/3/21 - 15:16
 */
@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    /**
     * 新增员工
     * @param employee
     * @return
     */
    @PostMapping
    public Result<String> save(@RequestBody Employee employee){

        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        // 保存员工信息
        employeeService.save(employee);

        return Result.ok("新增员工成功");
    }

    /**
     * 分页查询用户信息
     * @param page 分页对象
     * @param current 当前页
     * @param size 分页大小
     * @param name 用户姓名(搜索)
     * @return
     */
    @RequestMapping("/page")
    public Result<Page<Employee>> showEmployeeByPage(
            Page<Employee> page,
            @RequestParam("page") Integer current,
            @RequestParam("pageSize") Integer size,
            @RequestParam(value = "name",required = false) String name
    ){

        page.setCurrent(current);
        page.setSize(size);

        Long shopId = BaseContext.getShopId();

        //执行查询
        page = employeeService.getEmployeeByPage(page,name,shopId);

        return Result.ok(page);
    }

    /**
     * 修改员工信息
     * @return
     */
    @PutMapping
    public Result<String> updateEmployee(@RequestBody Employee employee){


        employeeService.updateById(employee);

        return Result.ok("修改成功");
    }

    /**
     * 根据id查询用户信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<Employee> getEmployeeById(@PathVariable Long id){
        Employee employee = employeeService.getById(id);
        if(employee != null){
            return Result.ok(employee);
        }
        return Result.error("没有查询到对应员工信息");
    }
}
