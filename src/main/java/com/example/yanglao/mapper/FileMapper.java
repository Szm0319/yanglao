package com.example.yanglao.mapper;

import com.example.yanglao.entity.File;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileMapper {
    void save(File file);
    File selectfilebyid(int id);
    Boolean existsbyid(int id);
}
