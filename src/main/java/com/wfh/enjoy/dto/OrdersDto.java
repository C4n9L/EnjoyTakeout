package com.wfh.enjoy.dto;

import com.wfh.enjoy.entity.OrderDetail;
import com.wfh.enjoy.entity.Orders;
import lombok.Data;

import java.util.List;

/**
 * @author wfh
 * @create 2023/5/24 - 21:26
 */
@Data
public class OrdersDto extends Orders {

    List<OrderDetail> orderDetailList;

 }
