package com.example.yanglao.entity;

import lombok.Data;

@Data
public class Customer {
    int customerId;
    int userId;
    String name;
    String sex;
    int age;
    String birthday;
    int phone;
    String notes;
    String address;
}
