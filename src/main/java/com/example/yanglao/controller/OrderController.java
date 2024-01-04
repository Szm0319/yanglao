package com.example.yanglao.controller;

import com.example.yanglao.entity.Order;
import com.example.yanglao.entity.OrderDetails;
import com.example.yanglao.service.CaiService;
import com.example.yanglao.service.OrderDetailService;
import com.example.yanglao.service.OrderService;
import com.example.yanglao.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class OrderController {
    @Autowired
    private OrderService orderService;
    private OrderDetailService orderDetailService;
    private UserService userService;
    private RedisTemplate<String,Object> redisTemplate;
    private HashOperations<String, Object, Object> hashOperations;
    private CaiService caiService;
    @GetMapping("/order/showall")
    public  ResponseEntity<List<Order>> showAllOrders(){
        return ResponseEntity.ok(orderService.showall());
    }
    //Order 需要给出userid，cainame，quantity
    @PostMapping("/order/create")
    public ResponseEntity<Map<String,Object>> createOrder(@RequestBody Order order){
        int userId = order.getUserId();
        Order neworder = new Order();
        neworder.setUserId(userId);
        neworder.setStatus("已提交订单");
        neworder.setOrdertime(LocalDateTime.now());
        System.out.println("创建订单的时间为："+LocalDateTime.now());
        orderService.insertOrder(order);
        int orderid = orderService.selectIdbyuser(userId);
        String carkey = "car" + userId;
        hashOperations = redisTemplate.opsForHash();
        Map<Object,Object> car = hashOperations.entries(carkey);
        int total_price = 0;
        for (Map.Entry<Object,Object> entry : car.entrySet()){
            String cai_name = (String) entry.getKey();
            int quantity = (int) entry.getValue();
            OrderDetails orderDetails = new OrderDetails();
            System.out.println(orderid);
            orderDetails.setOrderId(orderid);
            orderDetails.setCainame(cai_name);
            orderDetails.setQuantity(quantity);
            int price = caiService.selectPriceByName(cai_name);
            orderDetails.setPrice(price);
            orderDetailService.insertOrderDetails(orderDetails);
            int item_price = quantity * price;
            total_price = total_price+item_price;
        }
        System.out.println("总价格为："+total_price);
//        int totalprice = orderDetailService.gettotalprice(orderid);
        orderService.updataOrderPriceByid(orderid,total_price);
        redisTemplate.delete(carkey);
        Map<String,Object> response = new HashMap<>();
        response.put("message","订单已创建");
        response.put("total",total_price);
        return ResponseEntity.ok().body(response);
    }
    //支付订单
    @PostMapping("/order/Pay")
    public ResponseEntity<Map<String,Object>> PayOrder(@RequestParam("orderId") int orderId) {
        //第三方api付款
        //........
        String status = "已支付";
        orderService.updateOrderStatus(orderId,status);
        Map<String,Object> response = new HashMap<>();
        response.put("message","订单支付成功");
        return ResponseEntity.ok().body(response);
    }
    //配送订单
    @PostMapping("/order/send")
    public ResponseEntity<Map<String,Object>> sendOrder(@RequestParam int orderId,int employeeId) {
        String status = "配送中";
        orderService.updateOrderStatus(orderId,status);
        orderService.updateemployeeId(orderId,employeeId);
        Map<String,Object> response = new HashMap<>();
        response.put("message","订单配送中");
        return ResponseEntity.ok().body(response);
    }
    //完成订单
    @PostMapping("/order/finish")
    public ResponseEntity<Map<String,Object>> finishOrder(@RequestParam int orderId) {
        String status = "已完成";
        orderService.updateOrderStatus(orderId,status);
        orderService.updateSendtime(orderId);
        Map<String,Object> response = new HashMap<>();
        response.put("message","订单已完成");
        return ResponseEntity.ok().body(response);
    }
}
