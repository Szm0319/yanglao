package com.example.yanglao.controller;

import com.example.yanglao.entity.Cai;
import com.example.yanglao.entity.User;
import com.example.yanglao.service.CaiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class CaiController {
    @Autowired
    private CaiService caiService;
    @PostMapping("api/showcai")
    public ResponseEntity<List<Cai>> showcai(){
        List<Cai> caiList = caiService.selectAll();
        log.info(caiList.toString());
        return ResponseEntity.ok().body(caiList);
    }
    @PostMapping("api/addcai")
    public ResponseEntity<Map<String,String>> addcai(@RequestBody Cai cai){
        Cai cai1 = new Cai();
        cai1.setCainame(cai.getCainame());
        cai1.setPrice(cai.getPrice());
        cai1.setFenlei(cai.getFenlei());
        caiService.insertCai(cai1);
        Map<String, String> response = new HashMap<>();
        if (cai1 == null) {
            // 用户未找到，返回自定义消息
            response.put("message", "请输入完整的菜品信息");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        response.put("message", "添加成功！");
        return ResponseEntity.ok().body(response);
    }
}
