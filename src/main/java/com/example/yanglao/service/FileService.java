package com.example.yanglao.service;

import com.example.yanglao.entity.File;
import com.example.yanglao.mapper.FileMapper;
import org.springframework.stereotype.Service;

@Service
public interface FileService extends FileMapper {
    void save(File file);

    File selectfilebyid(int id);

    Boolean existsbyid(int id);
}
