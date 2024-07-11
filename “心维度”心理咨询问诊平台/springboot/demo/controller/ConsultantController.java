package com.example.demo.controller;

import com.example.demo.Service.ConsultantService;
import com.example.demo.entity.Consultant;
import com.example.demo.entity.ConsultantQueryParams;
import com.example.demo.mapper.ConsultantMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Consultant")
@CrossOrigin
public class ConsultantController {

    @Autowired
    ConsultantMapper consultantMapper;
    @Autowired
    ConsultantService consultantService;
    @RequestMapping
    public String insert(String trueName,Integer age,String address){
        return consultantMapper.insert(new Consultant(trueName,age,address))>0?"success!":"fail";
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<String> deleteConsultant(@PathVariable int id) {
        try {
            consultantService.deleteConsultantById(id);
            return ResponseEntity.ok("Consultant deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/search")
    public Map<String, Object> queryConsultants(@RequestBody ConsultantQueryParams params) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            List<Consultant> consultantsList = consultantService.queryConsultants(params);
            int total = consultantService.getTotalConsultants();

            resultMap.put("consultantsList", consultantsList);
            resultMap.put("total", total);
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("error", e.getMessage());
        }
        return resultMap;
    }

}
