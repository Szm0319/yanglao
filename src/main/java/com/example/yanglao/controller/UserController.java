package com.example.yanglao.controller;

import com.example.yanglao.entity.User;
import com.example.yanglao.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@Slf4j
public class UserController {
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
    @GetMapping("/user/showall")
    public ResponseEntity<List<User>> showuser(){
        List<User> users = userService.showall();
        return ResponseEntity.ok().body(users);
    }
    @PostMapping("/user/add")
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
    @PostMapping("/user/update")
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
    /*通过用户id查找*/
    @PostMapping("/user/select")
    public ResponseEntity<?> select(@RequestBody Map<String,Integer> body){
        Integer uid = Integer.valueOf(body.get("uid"));
        User user = userService.selectUserById(uid);
        if (user == null) {
            // 用户未找到，返回自定义消息
            Map<String, String> response = new HashMap<>();
            response.put("message", "查无此人");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        return ResponseEntity.ok().body(user);
    }
    @PostMapping("/user/delete")
    public ResponseEntity<Map<String,String>> deleteuser(@RequestBody Map<String,Integer> body){
        Integer uid = Integer.valueOf(body.get("uid"));
        userService.deleteUserById(uid);
        Map<String,String> response = new HashMap<>();
        response.put("message","删除成功！");
        return ResponseEntity.ok().body(response);
    }

}
