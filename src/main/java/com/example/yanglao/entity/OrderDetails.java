package com.example.yanglao.entity;

import lombok.Data;

@Data
public class OrderDetails {
    private int orderdetailId;
    private int orderId;
    private String cainame;
    private int quantity;
    private int price;
    private String type;
}
