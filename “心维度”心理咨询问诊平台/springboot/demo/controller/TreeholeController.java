package com.example.demo.controller;

import com.example.demo.Service.TreeholeService;
import com.example.demo.entity.CommentExt;
import com.example.demo.entity.CommentQueryParams;
import com.example.demo.entity.Treehole;
import com.example.demo.entity.TreeholeQueryParams;
import com.example.demo.mapper.TreeholeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/Treehole")
public class TreeholeController {
    @Autowired
    private TreeholeService treeholeService;

    @PostMapping("/search")
    public Map<String, Object> queryTreeholes(@RequestBody TreeholeQueryParams params) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            List<Treehole> treeholesList = treeholeService.queryTreeholes(params);
            int total = treeholeService.getTotalTreeholes();

            resultMap.put("treeholesList", treeholesList);
            resultMap.put("total", total);
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("error", e.getMessage());
        }
        return resultMap;
    }
}
