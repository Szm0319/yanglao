package com.example.yanglao.mapper;

import com.example.yanglao.entity.OrderDetails;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderDetailsMapper {
    void insertOrderDetails(OrderDetails orderDetails);

    int gettotalprice(int order_id);
}
