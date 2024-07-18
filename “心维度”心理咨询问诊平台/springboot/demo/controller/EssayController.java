package com.example.demo.controller;

import com.example.demo.entity.Announcement;
import com.example.demo.entity.Essay;
import com.example.demo.mapper.EssayMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Essay")
@CrossOrigin
public class EssayController {
    @Autowired
    private EssayMapper essayMapper;
    @GetMapping("/findAll")
    public List<Essay> findAll() {
        return essayMapper.findAll();
    }

    @PostMapping("/Pass/{id}")
    public ResponseEntity<String> passUser(@PathVariable  int id) {
        try {
            essayMapper.passEssayById(id);
            return ResponseEntity.ok("Essay pass successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


}
