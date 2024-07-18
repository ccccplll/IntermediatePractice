package com.example.demo.controller;

import com.example.demo.Service.CommentService;
import com.example.demo.entity.CommentExt;
import com.example.demo.entity.CommentQueryParams;
import com.example.demo.entity.Consultant;
import com.example.demo.entity.ConsultantQueryParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Comment")
@CrossOrigin
public class CommentController {
    @Autowired
    CommentService commentService;

    @PostMapping("/search")
    public Map<String, Object> queryComments(@RequestBody CommentQueryParams params) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            List<CommentExt> commentsList = commentService.queryComments(params);
            int total = commentService.getTotalComments();

            resultMap.put("commentsList", commentsList);
            resultMap.put("total", total);
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("error", e.getMessage());
        }
        return resultMap;
    }
}
