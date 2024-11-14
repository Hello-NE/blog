package com.example.blog.controller.Admin;


import com.example.blog.po.Project;
import com.example.blog.po.Result;
import com.example.blog.po.StatusCode;
import com.example.blog.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/admin")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/project/{id}/delete")
    public Result delete(@PathVariable Long id) {
        projectService.deleteProject(id);
        return new Result(true, StatusCode.OK, "删除项目成功", null);
    }

    @GetMapping("/projects")
    public Result getAllProjects() {
        return new Result(true, StatusCode.OK, "获取项目列表成功", projectService.listProject());
    }

    @PostMapping("/project")
    public Result post(@RequestBody Map<String, Project> para) {
        System.out.println(para);

        Project project = para.get("project");
        Project p;
        if (project.getId() == null) {
            p = projectService.saveProject(project);
        } else {
            p = projectService.updateProject(project.getId(), project);
        }
        if (p == null) {
            return new Result(false, StatusCode.ERROR, "操作失败", null);
        }

        return new Result(true, StatusCode.OK, "操作成功", p);
    }
}
