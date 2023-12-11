package com.example.yanglao.controller;

import com.example.yanglao.entity.User;
import com.example.yanglao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
@Controller
public class indexController {
    @Autowired
    private UserService userService;
    @GetMapping("/")
    public String index(){
        return "index";
    }
    @GetMapping("/welcome")
    public String welcome(){

        return "welcome";
    }
    @PostMapping("/api/adduser")
    public ResponseEntity<Map<String,String>> addusers(@RequestBody User user){
        User user1 = new User();
        user1.setUsertype(user.getUsertype());
        user1.setUsername(user.getUsername());
        user1.setPassword(user.getPassword());
        userService.save(user1);
        Map<String,String> response = new HashMap<>();
        response.put("message","新用户已创建");
        return ResponseEntity.ok().body(response);
    }
    @PostMapping("/api/updateuser")
    public ResponseEntity<Map<String,String>> update(@RequestBody User user){
        Integer uid = user.getUserId();
        User user1 = userService.selectUserById(uid);
        user1.setUsername(user.getUsername());
        user1.setPassword(user.getPassword());
        userService.save(user1);
        Map<String,String> response = new HashMap<>();
        response.put("message","'用户'"+uid+"已修改");
        System.out.println(uid);
        return ResponseEntity.ok().body(response);
    }
    @PostMapping("/api/selectuser")
    public ResponseEntity<User> select(@RequestParam("uid")int uid){
        User user1 = userService.selectUserById(uid);
        return ResponseEntity.ok().body(user1);
    }

}
