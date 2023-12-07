package com.example.yanglao.service.Impl;

import com.example.yanglao.entity.Order;
import com.example.yanglao.mapper.OrderMapper;
import com.example.yanglao.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    public void insertOrder(Order order){
         orderMapper.insertOrder(order);
    }

    @Override
    public int selectIdbyuser(int user_id) {
        return orderMapper.selectIdbyuser(user_id);
    }

    @Override
    public void updataOrderPriceByid(int order_id,int total_price) {
        orderMapper.updataOrderPriceByid(order_id,total_price);
    }
}
