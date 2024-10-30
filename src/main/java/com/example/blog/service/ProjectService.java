package com.example.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.blog.po.Project;

import java.util.List;

public interface ProjectService {
    List<Project> listProject();

    void deleteProject(Long id);

    Project saveProject(Project project);

    Project updateProject(Long id,Project project);
}
