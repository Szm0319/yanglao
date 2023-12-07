package com.example.yanglao.mapper;

import com.example.yanglao.entity.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {
    void insertOrder(Order order);
    int selectIdbyuser(int user_id);

    void updataOrderPriceByid(int order_id,int total_price);
}
