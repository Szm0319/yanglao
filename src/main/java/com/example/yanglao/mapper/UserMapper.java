package com.example.yanglao.mapper;

import com.example.yanglao.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    void save(User user);
    Boolean selectUserByUsername(String username);

    String selectpassword(String username);
    String selectUsertype(String username);

    Integer selectUserID(String username);

    List<User> showall();

}
