package com.example.blog.controller;

import com.example.blog.po.Result;
import com.example.blog.po.StatusCode;
import com.example.blog.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class ProjectShowController {
    @Autowired
    private ProjectService projectService;

    @GetMapping("/projects")
    public Result projects() {
        return new Result(true, StatusCode.OK, "获取项目列表成功", projectService.listProject());
    }
}