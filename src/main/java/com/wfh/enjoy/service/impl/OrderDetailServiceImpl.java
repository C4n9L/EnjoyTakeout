package com.wfh.enjoy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wfh.enjoy.entity.OrderDetail;
import com.wfh.enjoy.mapper.OrderDetailMapper;
import com.wfh.enjoy.service.OrderDetailService;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {

}