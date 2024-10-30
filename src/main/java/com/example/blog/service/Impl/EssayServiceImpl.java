package com.example.blog.service.Impl;

import com.example.blog.dao.EssayRepository;
import com.example.blog.po.Essay;
import com.example.blog.service.EssayService;
import com.example.blog.util.MyBeanUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EssayServiceImpl implements EssayService {

    @Autowired
    private EssayRepository essayRepository;


    @Override
    public List<Essay> listEssay() {
        return essayRepository.findAll();
    }

    @Override
    public void deleteEssay(Long id) {
        essayRepository.deleteById(id);
    }

    @Override
    public Essay saveEssay(Essay essay) {
        essay.setCreateTime(new Date());
        return essayRepository.save(essay);
    }

    @Override
    public Essay updateEssay(Long id, Essay essay) {
        Essay e = essayRepository.getOne(id);
        BeanUtils.copyProperties(essay,e, MyBeanUtils.getNullPropertyNames(essay));
        return essayRepository.save(e);
    }


}