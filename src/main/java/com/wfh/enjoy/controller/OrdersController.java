package com.wfh.enjoy.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wfh.enjoy.dto.OrdersDto;
import com.wfh.enjoy.entity.Orders;
import com.wfh.enjoy.service.IOrdersService;
import com.wfh.enjoy.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author wfh
 * @create 2023/4/6 - 19:35
 */
@Slf4j
@RestController
@RequestMapping("/order")
public class OrdersController {

    @Autowired
    private IOrdersService ordersService;

    @GetMapping("/userPage")
    public Result<Page<OrdersDto>> showOrdersByPage(
            Page<OrdersDto> page,
            @RequestParam("page") Integer current,
            @RequestParam("pageSize") Integer size
    ){
        page.setCurrent(current);
        page.setSize(size);
        page = ordersService.getUserOrders(page);
        return Result.ok(page);
    }

    @GetMapping("/page")
    public Result<Page<Orders>> showOrdersPage(
            Page<Orders> page,
            @RequestParam("page") Integer current,
            @RequestParam("pageSize") Integer size
    ){
        page.setCurrent(current);
        page.setSize(size);
        page = ordersService.getOrdersPage(page);
        return Result.ok(page);

    }

    /**
     * 用户下单
     * @param orders
     * @return
     */
    @PostMapping("/submit")
    public Result<String> submit(@RequestBody Orders orders){
        log.info("订单数据：{}",orders);
        ordersService.submit(orders);
        return Result.ok("下单成功");
    }


    @PutMapping
    public Result<String> updateOrderStatus(@RequestBody Orders orders){

        ordersService.updateById(orders);

        return Result.ok("修改成功");
    }

}
