package com.wfh.enjoy.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wfh.enjoy.entity.Employee;
import com.wfh.enjoy.service.IEmployeeService;
import com.wfh.enjoy.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author wfh
 * @create 2023/3/31 - 13:30
 */

@Slf4j
@RestController
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private IEmployeeService IEmployeeService;

    /**
     * 员工登录
     * @param employee
     * @param session
     * @return
     */
    @PostMapping("/login")
    public Result<Employee> login(@RequestBody Employee employee, HttpSession session){

        //1、将页面提交的密码password进行md5加密处理
        String password = DigestUtils.md5DigestAsHex(employee.getPassword().getBytes());

        //2、根据页面提交的用户名username查询数据库
        Employee emp = IEmployeeService.getOne(new LambdaQueryWrapper<Employee>()
                .eq(Employee::getUsername, employee.getUsername())
        );

        //3、如果没有查询到则返回登录失败结果
        if(emp == null){
            return Result.error("登陆失败");
        }

        //4、密码比对，如果不一致则返回登录失败结果
        if(!emp.getPassword().equals(password)){
            return Result.error("登陆失败");
        }

        //5、查看员工状态，如果为已禁用状态，则返回员工已禁用结果
        if(emp.getStatus() == 0){
            return Result.error("账号被封禁");
        }

        //6、登录成功，将员工id存入Session并返回登录成功结果
        session.setAttribute("loginUser",emp.getId());
        session.setAttribute("shopId",emp.getShopId());
        log.info("shopId:{}",emp.getShopId());

        return Result.ok(emp);
    }


    /**
     * 员工登出
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public Result<String> logout(HttpServletRequest request){

        //清理Session中保存的当前登录员工的id
        request.getSession().removeAttribute("loginUser");

        return Result.ok("退出成功");
    }
}
