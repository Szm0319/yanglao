package com.example.yanglao.service.Impl;

import com.example.yanglao.entity.OrderDetails;
import com.example.yanglao.mapper.OrderDetailsMapper;
import com.example.yanglao.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    private OrderDetailsMapper orderDetailsMapper;

    @Override
    public void insertOrderDetails(OrderDetails orderDetails) {
        orderDetailsMapper.insertOrderDetails(orderDetails);
    }

    @Override
    public int gettotalprice(int order_id) {
        return orderDetailsMapper.gettotalprice(order_id);
    }
}
