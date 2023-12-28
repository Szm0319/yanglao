package com.example.yanglao.service;

import com.example.yanglao.entity.Order;
import com.example.yanglao.mapper.OrderMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService extends OrderMapper {
    void insertOrder(Order order);

    @Override
    int selectIdbyuser(int user_id);

    void updataOrderPriceByid(int order_id,int total_price);

    void updateOrderStatus(int order_id,String status);
    void updateemployeeId(int orderId,int employeeId);
    void updateSendtime(int orderId);
    List<Order> showall();


}
