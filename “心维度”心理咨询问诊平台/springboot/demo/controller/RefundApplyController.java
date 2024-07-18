package com.example.demo.controller;

import com.example.demo.entity.Essay;
import com.example.demo.entity.RefundApply;
import com.example.demo.entity.Umessage;
import com.example.demo.mapper.EssayMapper;
import com.example.demo.mapper.RefundApplyMapper;
import com.example.demo.mapper.UmessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/Refund")
@CrossOrigin
public class RefundApplyController {
    @Autowired
    private RefundApplyMapper refundApplyMapper;
    @Autowired
    private UmessageMapper umessageMapper;

    @GetMapping("/findAll")
    public List<RefundApply> findAll() {
        return refundApplyMapper.findAll();
    }

    @PostMapping("/refund/{id}")
    public ResponseEntity<String> passUser(@PathVariable int id) {
        try {
            Umessage umessage= new Umessage();

            umessage.setUid(refundApplyMapper.findUidById(id));
            umessage.setContext("您的预约编号"+refundApplyMapper.findAidById(id)+"退款申请已通过。");
            umessage.setCreateTime(new Date());
            umessage.setType("退款提醒");
            umessageMapper.insert(umessage);
            refundApplyMapper.refundById(id);

            return ResponseEntity.ok("Refund back successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
