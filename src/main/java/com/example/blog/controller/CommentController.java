package com.example.blog.controller;

import com.example.blog.po.Comment;
import com.example.blog.po.Result;
import com.example.blog.po.StatusCode;
import com.example.blog.po.User;
import com.example.blog.service.BlogService;
import com.example.blog.service.CommentService;
import com.example.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
public class CommentController {
    private final String avatar = "/images/avatar.png";

    @Autowired
    private CommentService commentService;
    @Autowired
    private BlogService blogService;
    @Autowired
    private UserService userService;

    //获取评论集合
    @GetMapping("/comments/{blogId}")
    public Result comments(@PathVariable Long blogId) {
        return new Result(true, StatusCode.OK, "获取博客评论成功", commentService.listCommentByBlogId(blogId));
    }

    @PostMapping("/comments")
    public Result post(@RequestBody Map<String, Object> para) {
        System.out.println(para);
        String content = (String) para.get("content");
        Long blogId = Long.parseLong(para.get("blogId").toString());
        Long userId = Long.parseLong(para.get("userId").toString());
        long parentId = Long.parseLong(para.get("parentId").toString());
        User user = userService.findUserById(userId);
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setBlog(blogService.getBlog(blogId));
        comment.setUserId(userId);
        comment.setNickname(user.getNickname());
        comment.setEmail(user.getEmail());
        comment.setAvatar(user.getAvatar());
        comment.setAdminComment(user.getType().equals("1"));
        if (parentId != -1) {
            comment.setParentComment(commentService.getCommentById(parentId));
        }
        System.out.println(comment);
        Comment newComment = commentService.saveComment(comment);
        return new Result(true, StatusCode.OK, "评论发表成功！", newComment);
    }

    //删除评论
    @GetMapping("/comments/{id}/delete")
    public Result delete(@PathVariable Long id) {
        commentService.deleteComment(id);
        return new Result(true, StatusCode.OK, "删除评论成功", null);
    }

}