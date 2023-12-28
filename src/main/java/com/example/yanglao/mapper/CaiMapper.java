package com.example.yanglao.mapper;

import com.example.yanglao.entity.Cai;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CaiMapper {
    Cai selectById(int id);
    Cai selectByCainame(String cainame);
    void deleteById(int id);
    void updateById(Cai cai);

    void insertCai(Cai cai);
    List<Cai> selectAll();
    List<Cai> selectByPage(int pageset, int size);

    String selectNameByid(int id);

    int selectPriceByName(String name);
}
