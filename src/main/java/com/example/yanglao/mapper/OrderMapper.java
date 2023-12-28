package com.example.yanglao.mapper;

import com.example.yanglao.entity.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    void insertOrder(Order order);
    int selectIdbyuser(int userId);

    void updataOrderPriceByid(int orderId,int totalprice);
    double selectPriceByid(int oderId);

    Order selectOrderByid(int orderId);
    void updateOrderStatus(int orderId,String status);
    void updateemployeeId(int orderId,int employeeId);
    void updateSendtime(int orderId);
    List<Order> showall();
}
