package com.wfh.enjoy.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wfh.enjoy.dto.OrdersDto;
import com.wfh.enjoy.entity.Orders;

/**
 * @author wfh
 * @create 2023/4/6 - 19:34
 */
public interface IOrdersService extends IService<Orders> {
    void submit(Orders orders);

    Page<Orders> getOrdersPage(Page<Orders> page);

    Page<OrdersDto> getUserOrders(Page<OrdersDto> page);
}
