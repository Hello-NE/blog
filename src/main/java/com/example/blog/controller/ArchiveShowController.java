package com.example.blog.controller;

import com.example.blog.po.Result;
import com.example.blog.po.StatusCode;
import com.example.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin
public class ArchiveShowController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/archiveBlog")
    @ResponseBody  // 添加这个注解以返回JSON
    public Result<Model> archives() {
        return new Result(true, StatusCode.OK, "查询博客列表成功", blogService.archiveBlog());
    }

    @GetMapping("/countBlog")
    public Result count() {
        return new Result(true, StatusCode.OK, "查询博客列表成功", blogService.countBlog());
    }
}