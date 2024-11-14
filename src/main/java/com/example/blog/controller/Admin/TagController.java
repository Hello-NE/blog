package com.example.blog.controller.Admin;


import com.example.blog.po.Result;
import com.example.blog.po.StatusCode;
import com.example.blog.po.Tag;
import com.example.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
@CrossOrigin
public class TagController {

    @Autowired
    private TagService tagService;

    @PostMapping("/tags")
    public Result post(@RequestBody Map<String, Tag> para) {
        Tag tag = para.get("tag");
        if (tag.getId() == null) {
            Tag t = tagService.getTagByName(tag.getName());
            if (t != null) {
                return new Result(false, StatusCode.OK, "不能添加重复标签", null);
            }
        } else {
            List<Tag> tagList = tagService.listByNameExceptSelf(tag.getId(), tag.getName());
            if (!tagList.isEmpty()) {
                return new Result(false, StatusCode.OK, "分类名称已经存在", null);
            }
        }

        Tag t = tagService.saveTag(tag);
        if (t == null) {
            return new Result(false, StatusCode.ERROR, "修改失败", null);
        }
        return new Result(true, StatusCode.OK, "修改成功", t);
    }

    @GetMapping("/tags/{id}/delete")

    public Result delete(@PathVariable Long id) {
        tagService.deleteTag(id);
        return new Result(true, StatusCode.OK, "删除成功", null);
    }
}
