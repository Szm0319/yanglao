package com.example.yanglao.service;

import com.example.yanglao.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {
    List<Customer> showall();
    void  addCustomer(Customer customer);
    void deleteCustomer(int customerId);
    void updateCustomer(Customer customer);
    Customer findCustomerbyName(String name);
    List<Customer> medical();
}
