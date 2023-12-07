package com.example.yanglao.service.Impl;

import com.example.yanglao.entity.File;
import com.example.yanglao.mapper.FileMapper;
import com.example.yanglao.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private FileMapper fileMapper;

    @Override
    public void save(File file) {
        fileMapper.save(file);
    }

    public File selectfilebyid(int id){
       return fileMapper.selectfilebyid(id);
    }

    @Override
    public Boolean existsbyid(int id) {
        return fileMapper.existsbyid(id);
    }


}
