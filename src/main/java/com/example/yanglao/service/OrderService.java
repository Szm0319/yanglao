package com.example.yanglao.service;

import com.example.yanglao.entity.Order;
import com.example.yanglao.mapper.OrderMapper;
import org.springframework.stereotype.Service;

@Service
public interface OrderService extends OrderMapper {
    void insertOrder(Order order);

    @Override
    int selectIdbyuser(int user_id);

    void updataOrderPriceByid(int order_id,int total_price);


}
