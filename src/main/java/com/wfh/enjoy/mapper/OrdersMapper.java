package com.wfh.enjoy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wfh.enjoy.dto.OrdersDto;
import com.wfh.enjoy.entity.Orders;
import org.springframework.stereotype.Repository;

/**
 * @author wfh
 * @create 2023/4/6 - 19:33
 */
@Repository
public interface OrdersMapper extends BaseMapper<Orders> {
    Page<Orders> getOrdersPage(Page<Orders> page, Long shopId);

    Page<OrdersDto> getUserOrders(Page<OrdersDto> page);
}
