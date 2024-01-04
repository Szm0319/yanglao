package com.example.yanglao.controller;

import com.example.yanglao.entity.Customer;
import com.example.yanglao.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    /*展示所有老人信息*/
    @GetMapping("/customer/showall")
    public ResponseEntity<List<Customer>> showAll() {
        List<Customer> customers = customerService.showall();
        return ResponseEntity.ok(customers);
    }
    /*通过名字查找*/
    @GetMapping("/customer/findByName")
    public ResponseEntity<Customer> findByName(@RequestParam String name) {
        Customer customer = customerService.findCustomerbyName(name);
        return ResponseEntity.ok(customer);
    }
    /*增删改老人信息*/
    @PostMapping("/customer/add")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        customerService.addCustomer(customer);
        return ResponseEntity.ok(customer);
    }

    @PostMapping("/customer/delete")
    public ResponseEntity<Map<String, String>> deleteCustomer(@RequestParam int customerId) {
        customerService.deleteCustomer(customerId);
        Map<String, String> map = Map.of("msg", "删除成功");
        return ResponseEntity.ok(map);
    }

    @PostMapping("/customer/update")
    public ResponseEntity<Map<String, String>> updateCustomer(@RequestBody Customer customer) {
        customerService.updateCustomer(customer);
        Map<String, String> map = Map.of("msg", "修改成功");
        return ResponseEntity.ok(map);
    }
    /*查找需要就医服务的老人
    * medical字段默认为0，如果需要就医陪同则设置为1*/
    @GetMapping("/customer/medical")
    public ResponseEntity<List<Customer>> medical() {
        List<Customer> customers = customerService.medical();
        return ResponseEntity.ok(customers);
    }
}
