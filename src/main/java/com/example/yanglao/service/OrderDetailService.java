package com.example.yanglao.service;

import com.example.yanglao.entity.OrderDetails;
import com.example.yanglao.mapper.OrderDetailsMapper;
import org.springframework.stereotype.Service;

@Service
public interface OrderDetailService extends OrderDetailsMapper {
    void insertOrderDetails(OrderDetails orderDetails);

    int gettotalprice(int order_id);


}
