package com.example.yanglao.service.Impl;

import com.example.yanglao.entity.Order;
import com.example.yanglao.mapper.OrderMapper;
import com.example.yanglao.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    public void insertOrder(Order order){
        orderMapper.insertOrder(order);
    }

    @Override
    public int selectIdbyuser(int userId) {
        return orderMapper.selectIdbyuser(userId);
    }

    @Override
    public void updataOrderPriceByid(int orderId,int totalprice) {
        orderMapper.updataOrderPriceByid(orderId,totalprice);
    }


    @Override
    public void updateOrderStatus(int orderId, String status) {
        orderMapper.updateOrderStatus(orderId,status);
    }

    @Override
    public void updateemployeeId(int orderId, int employeeId) {
        orderMapper.updateemployeeId(orderId,employeeId);
    }

    @Override
    public void updateSendtime(int orderId) {
        orderMapper.updateSendtime(orderId);
    }
    public List<Order> showall(){
        return orderMapper.showall();
    }


    @Override
    public double selectPriceByid(int orderId) {
        return orderMapper.selectPriceByid(orderId);
    }

    @Override
    public Order selectOrderByid(int orderId) {
        return orderMapper.selectOrderByid(orderId);
    }
}
