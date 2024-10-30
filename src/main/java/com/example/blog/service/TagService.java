package com.example.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.blog.po.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TagService  {


    Tag saveTag(Tag tag);

    Tag getTag(Long id);

    Tag getTagByName(String name);

    List<Tag> listTag();

    Page<Tag> listTag(Pageable pageable);

    List<Tag> listTagTop(Integer size);

    List<Tag> listTag(String ids);

    Tag updateTag(Long id,Tag tag);

    void deleteTag(Long id);

    List<Tag> listByNameExceptSelf(Long id, String name);

}
