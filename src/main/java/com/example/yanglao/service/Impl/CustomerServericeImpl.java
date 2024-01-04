package com.example.yanglao.service.Impl;

import com.example.yanglao.entity.Customer;
import com.example.yanglao.mapper.CustomerMapper;
import com.example.yanglao.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CustomerServericeImpl implements CustomerService {
    @Autowired
    private CustomerMapper customerMapper;
    public List<Customer> showall(){
        return customerMapper.showall();
    }

    @Override
    public void addCustomer(Customer customer) {
        customerMapper.addCustomer(customer);
    }

    @Override
    public void deleteCustomer(int customerId) {
        customerMapper.deleteCustomer(customerId);
    }

    @Override
    public void updateCustomer(Customer customer) {
        customerMapper.updateCustomer(customer);
    }

    @Override
    public Customer findCustomerbyName(String name) {
        return customerMapper.findCustomerbyName(name);
    }
    public List<Customer> medical(){
        return customerMapper.medical();
    }
}
