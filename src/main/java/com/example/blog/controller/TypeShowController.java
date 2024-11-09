package com.example.blog.controller;


import com.example.blog.po.Result;
import com.example.blog.po.StatusCode;
import com.example.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
public class TypeShowController {
    @Autowired
    private TypeService typeService;

    @GetMapping("/getTypeList")
    public Result getTypeList() {
        return new Result(true, StatusCode.OK, "获取博客分类成功", typeService.listTypeTop(6));
    }

    @GetMapping("/getFullTypeList")
    public Result getFullTypeList() {
        return new Result(true, StatusCode.OK, "获取博客全部分类成功", typeService.listType());
    }

}