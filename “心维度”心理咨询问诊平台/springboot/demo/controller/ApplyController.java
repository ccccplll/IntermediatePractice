package com.example.demo.controller;

import com.example.demo.Service.ApplyService;
import com.example.demo.entity.Apply;
import com.example.demo.entity.ApplyExt;
import com.example.demo.entity.ApplyQueryParams;

import com.example.demo.mapper.ApplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Apply")
@CrossOrigin
public class ApplyController {

    @Autowired
    ApplyMapper applyMapper;
    @Autowired
    ApplyService applyService;
    @PostMapping("/search")
    public Map<String, Object> queryApplys(@RequestBody ApplyQueryParams params) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            List<ApplyExt> applysList = applyService.queryApplys(params);
            int total = applyService.getTotalApplys();
            System.out.println(applysList);
            resultMap.put("applysList", applysList);
            resultMap.put("total", total);
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("error", e.getMessage());
        }
        return resultMap;
    }

    @PostMapping("/Pass/{id}")
    public ResponseEntity<String> passUser(@PathVariable  int id) {
        try {
            applyService.passApplyById(id);
            return ResponseEntity.ok("Apply pass successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/Unpass/{id}")
    public ResponseEntity<String> unpassUser(@PathVariable  int id) {
        try {
            applyService.unpassApplyById(id);
            return ResponseEntity.ok("Apply unpass successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
