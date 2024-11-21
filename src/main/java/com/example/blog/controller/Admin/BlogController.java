package com.example.blog.controller.Admin;

import com.example.blog.po.Blog;
import com.example.blog.po.Result;
import com.example.blog.po.StatusCode;
import com.example.blog.po.Tag;
import com.example.blog.service.BlogService;
import com.example.blog.service.TagService;
import com.example.blog.service.TypeService;
import com.example.blog.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;


    @PostMapping("/getBlogList")
    public Result getBlogList(@RequestBody Map<String, Object> para) {
        int pageNum = (int) para.get("pageNum");
        int pageSize = (int) para.get("pageSize");
        BlogQuery blogQuery = new BlogQuery();
        if (para.get("typeId") != null) {
            blogQuery.setTypeId(Long.parseLong(para.get("typeId").toString()));
        }
        blogQuery.setTitle(para.get("title").toString());
        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize, sort);

        return new Result(true, StatusCode.OK, "获取博客列表成功", blogService.listBlog(pageable, blogQuery));
    }

    //发布博客
    @PostMapping("/blogs")
    public Result post(@RequestBody Map<String, Blog> para) {
        System.out.println(para);
        Blog blog = para.get("blog");
        blog.setType(typeService.getType(blog.getType().getId()));
        blog.setTags(tagService.listTag(blog.getTagIds()));
//        blog.setId(new Random().nextLong());
        List<Tag> tags = blog.getTags();

        Blog b;
        if (blog.getId() == null) {

            b = blogService.saveBlog(blog);
        } else {
            b = blogService.updateBlog(blog.getId(), blog);
        }
        if (b == null) {
            return new Result(false, StatusCode.ERROR, "操作失败", null);
        }
        return new Result(true, StatusCode.OK, "操作成功", b);
    }

    @GetMapping("/search")
    public Result search(@PageableDefault(size = 8, sort = {"createTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                         @RequestParam String keyword) {
        return new Result(true, StatusCode.OK, "获取搜索博客成功", blogService.listBlog(keyword, pageable));
    }

    @GetMapping("/blogs/{id}/delete")
    public Result delete(@PathVariable Long id) {
        blogService.deleteBlog(id);
        return new Result();
    }

    @GetMapping("/dealDeletedTag/{id}")
    public Result dealDeletedTag(@PathVariable Long id) {
        Tag tag = tagService.getTag(id);
        System.out.println(tag);
        if (tag.getBlogs().isEmpty()) {
            System.out.println("去除无用标签");
            tagService.deleteTag(id);
        }
        return new Result(true, StatusCode.OK, "处理标签成功", null);
    }

}
