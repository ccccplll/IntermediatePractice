package com.example.demo.controller;

import com.example.demo.Service.PageContentService;
import com.example.demo.entity.Page;
import com.example.demo.entity.PageContentExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/PageContent")
@CrossOrigin
public class PageContentController {
    @Autowired
    private PageContentService pageContentService;


    @GetMapping("/findCommentsByPageId/{id}")
    public ResponseEntity<List<PageContentExt>> findById(@PathVariable("id") int id) {
        try {
            List<PageContentExt> comments = pageContentService.findById(id);
            System.out.println(comments);
            return ResponseEntity.ok(comments);
        } catch (Exception e) {
            // 打印错误日志
            e.printStackTrace();
            // 返回 500 错误状态码
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
