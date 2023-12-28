package com.example.yanglao.controller;

import com.example.yanglao.entity.Cai;
import com.example.yanglao.entity.User;
import com.example.yanglao.service.CaiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class CaiController {
    @Autowired
    private CaiService caiService;

    @GetMapping("/cai/showall")
    public ResponseEntity<List<Cai>> showcai() {
        List<Cai> caiList = caiService.selectAll();
        log.info(caiList.toString());
        return ResponseEntity.ok().body(caiList);
    }

    @PostMapping("/cai/add")
    public ResponseEntity<Map<String, String>> addcai(@RequestBody Cai cai) {
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

    @PostMapping("/cai/delete")
    public ResponseEntity<Map<String, String>> deletecai(@RequestParam("id") int id) {
        caiService.deleteById(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "删除成功");
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/cai/update")
    public ResponseEntity<Map<String, String>> updatecai(@RequestBody Cai cai) {
        Cai seletecai = caiService.selectById(cai.getCaiId());
        seletecai.setCainame(cai.getCainame());
        seletecai.setPrice(cai.getPrice());
        seletecai.setFenlei(cai.getFenlei());
        Map<String, String> response = new HashMap<>();
        response.put("message", "修改成功");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/cai/select")
    public ResponseEntity<Cai> selectcai(@RequestParam String cainame) {
        Cai cai = caiService.selectByCainame(cainame);
        return ResponseEntity.ok(cai);
    }

}
