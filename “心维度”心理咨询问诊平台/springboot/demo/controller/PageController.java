package com.example.demo.controller;

import com.example.demo.Service.PageService;
import com.example.demo.entity.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Page")
@CrossOrigin
public class PageController {

    @Autowired
    private PageService pageService;

    @GetMapping("/findAll")
    public List<Page> findAll() {
        return pageService.findAll();
    }

    @GetMapping("/findById/{id}")
    public Page findById(@PathVariable("id") int id) {
        return pageService.findById(id);
    }

    @DeleteMapping("/deleteById/{id}")
    public int deleteById(@PathVariable("id") int id) {
        return pageService.deleteById(id);
    }

    @PostMapping("/insert")
    public int insert(@RequestBody Page page) {
        return pageService.insert(page);
    }
}
