package com.example.yanglao.service.Impl;

import com.example.yanglao.entity.User;
import com.example.yanglao.mapper.UserMapper;
import com.example.yanglao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void save(User user) {
        userMapper.save(user);
    }
    public Boolean selectUserByUsername(String username){
        return userMapper.selectUserByUsername(username);
    }
    public String selectpassword(String username){
        return userMapper.selectpassword(username);
    }
    public String selectUsertype(String username){
        return userMapper.selectUsertype(username);
    }
    public Integer selectUserID(String username){
        return userMapper.selectUserID(username);
    }
}
