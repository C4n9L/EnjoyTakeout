package com.wfh.enjoy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wfh.enjoy.entity.ShoppingCart;
import com.wfh.enjoy.mapper.ShoppingCartMapper;
import com.wfh.enjoy.service.ShoppingCartService;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {

}
