package com.example.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.blog.po.Essay;

import java.util.List;

public interface EssayService  {
    List<Essay> listEssay();

    void deleteEssay(Long id);

    Essay saveEssay(Essay essay);

    Essay updateEssay(Long id,Essay essay);
}
