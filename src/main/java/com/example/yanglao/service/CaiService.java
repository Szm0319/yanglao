package com.example.yanglao.service;

import com.example.yanglao.entity.Cai;
import com.example.yanglao.mapper.CaiMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CaiService extends CaiMapper{
    @Override
    Cai selectById(int id);
    Cai selectByCainame(String cainame);

    @Override
    void deleteById(int id);

    @Override
    void insertCai(Cai cai);

    @Override
    void updateById(Cai cai);

    @Override
    List<Cai> selectAll();

    List<Cai> selectByPage(int pageset, int size);

    @Override
    String selectNameByid(int id);

    int selectPriceByName(String name);

}
