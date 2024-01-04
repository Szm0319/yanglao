package com.example.yanglao.mapper;

import com.example.yanglao.entity.Customer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CustomerMapper {
    List<Customer> showall();
    void  addCustomer(Customer customer);
    void deleteCustomer(int id);
    void updateCustomer(Customer customer);
    Customer findCustomerbyName(String name);
    List<Customer> medical();

}
