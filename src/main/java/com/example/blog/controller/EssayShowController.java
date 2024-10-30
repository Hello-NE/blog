package com.example.blog.controller;


import com.example.blog.po.Result;
import com.example.blog.po.StatusCode;
import com.example.blog.service.EssayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class EssayShowController {
    @Autowired
    private EssayService essayService;

    @GetMapping("/essays")
    public Result essays() {
        return new Result(true, StatusCode.OK, "获取随笔列表成功", essayService.listEssay());
    }
}