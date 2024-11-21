package com.example.blog.service.Impl;

import com.example.blog.dao.TagRepository;
import com.example.blog.po.Blog;
import com.example.blog.po.Tag;
import com.example.blog.service.TagService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Transactional
    @Override
    public Tag saveTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Transactional
    @Override
    public Tag getTag(Long id) {
        return tagRepository.getOne(id);
    }

    @Transactional
    @Override
    public Tag getTagByName(String name) {
        return tagRepository.findByName(name);
    }

    @Override
    public List<Tag> listTag() {
        List<Tag> tags = tagRepository.findAll();
        return reduceTagsAttributes(tags);
    }

    @Transactional
    @Override
    public Page<Tag> listTag(Pageable pageable) {
        Page<Tag> tags = tagRepository.findAll(pageable);
        return tags;
    }

    @Override
    public List<Tag> listTagTop(Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "blogs.size");
        Pageable pageable = PageRequest.of(0, size, sort);
        List<Tag> tags = tagRepository.findTop(pageable);
        return reduceTagsAttributes(tags);
    }

    @Override
    public List<Tag> listTag(String ids) {
        System.out.println(ids);
        List<Tag> tags = tagRepository.findAllById(convertToList(ids));
        return reduceTagsAttributes(tags);
    }


    private List<Long> convertToList(String ids) {
        System.out.println(ids);
        List<Long> list = new ArrayList<>();
        if (!"".equals(ids) && ids != null) {
            String[] idarray = ids.split(",");
            for (int i = 0; i < idarray.length; i++) {
                list.add(new Long(idarray[i]));
            }
        }
        return list;
    }

    private List<Tag> reduceTagsAttributes(List<Tag> tags) {
        System.out.println(tags);
        tags.forEach(tag -> {
            List<Blog> blogs = tag.getBlogs();
            blogs.forEach(blog -> {
                blog.setContent("");
                blog.setComments(null);
            });
            tag.setBlogs(blogs);
        });
        return tags;
    }


    @Transactional
    @Override
    public Tag updateTag(Long id, Tag tag) {
        Tag t = tagRepository.getOne(id);
        BeanUtils.copyProperties(tag, t);
        return tagRepository.save(t);
    }

    @Transactional
    @Override
    public void deleteTag(Long id) {
        tagRepository.deleteById(id);
    }

    @Override
    public List<Tag> listByNameExceptSelf(Long id, String name) {
        return tagRepository.findByNameExceptSelf(id, name);
    }

}
