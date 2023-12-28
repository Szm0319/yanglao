package com.example.yanglao.entity;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Order {
    private int orderId;
    private int userId;
    private int totalprice;
    private String status;
    private LocalDateTime ordertime;
    private LocalDateTime paytime;
    private int employeeId;
    private LocalDateTime sendtime;
}
