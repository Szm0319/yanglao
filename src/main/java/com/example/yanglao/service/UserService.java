package com.example.yanglao.service;

import com.example.yanglao.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void save(User user);
    Boolean selectUserByUsername(String username);
    String selectpassword(String username);

    String selectUsertype(String username);
    Integer selectUserID(String username);

}
