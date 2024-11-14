package com.example.blog.controller.Admin;


import com.example.blog.po.Result;
import com.example.blog.po.StatusCode;
import com.example.blog.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdminIndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private UserService userService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @Autowired
    private CommentService commentService;

    //获取博客总量
    @GetMapping("/getBlogCount")
    public Result getBlogCount() {
        return new Result(true, StatusCode.OK, "获取博客总数成功", blogService.countBlog());
    }


    //获取总阅读量
    @GetMapping("/getViewCount")
    public Result getViewCount() {

        return new Result(true, StatusCode.OK, "获取阅读总数成功", blogService.countViews());
    }

    //获取总点赞数
    @GetMapping("/getAppreciateCount")
    public Result getAppreciateCount() {
        return new Result(true, StatusCode.OK, "获取点赞总数成功", blogService.countAppreciate());
    }

    //获取总评论数
    @GetMapping("/getCommentCount")
    public Result getCommentCount() {
        return new Result(true, StatusCode.OK, "获取评论总数成功", blogService.countComment());
    }

    //根据月份统计阅读量
    @GetMapping("/getViewCountByMonth")
    public Result getBlogViewsByMonth() {
        return new Result(true, StatusCode.OK, "获取按月份统计阅读总数成功", blogService.ViewCountByMonth());
    }

    //根据月份统计博客发表数
    @GetMapping("/getBlogCountByMonth")
    public Result getBlogCountByMonth() {
        return new Result(true, StatusCode.OK, "获取按月份统计发表总数成功", blogService.BlogCountByMonth());
    }

    //根据月份统计评论数
    @GetMapping("/getCommentCountByMonth")
    public Result getCommentCountByMonth() {
        return new Result(true, StatusCode.OK, "获取按月份统计评论总数成功", commentService.CommentCountByMonth());
    }

    //根据月份统计评论数
    @GetMapping("/getAppreciateCountByMonth")
    public Result getAppreciateCountByMonth() {
        return new Result(true, StatusCode.OK, "获取按月份统计评论总数成功", blogService.appreciateCountByMonth());
    }

    @GetMapping("/getFullTagList")
    public Result getFullTagList() {
        return new Result(true, StatusCode.OK, "获取所有博客标签成功", tagService.listTag());
    }

    @GetMapping("/getFullTypeList")
    public Result getFullTypeList() {
        return new Result(true, StatusCode.OK, "获取博客全部分类成功", typeService.listType());
    }

    @GetMapping("/getCommentList")
    public Result getCommentList() {
        return new Result(true, StatusCode.OK, "获取评论列表成功", commentService.listComment());
    }

    @GetMapping("/getUserAreaList")
    public Result getuserAreaList() {
        return new Result(true, StatusCode.OK, "获取用户地址列表成功", userService.listUser());
    }

}
