package com.example.blog.pojo;

import com.example.blog.queryVo.DetailedBlog;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Comment {
    private Long id;
    private String email;
    private String content;
    private String nickname;

    private String avatar;
    private Date createTime;

    private Long blogId;
    //父评论id 用来标识子评论属于哪个父评论的
    private Long parentCommentId;
    private String parentNickname;
    //回复评论
    private List<Comment> replyComments = new ArrayList<>();
    private Comment parentComment;//做扩展用，展示没有什么用，评论内容都存在了content字段中
    private boolean adminComment;

    private DetailedBlog blog;

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", content='" + content + '\'' +
                ", avatar='" + avatar + '\'' +
                ", createTime=" + createTime +
                ", blogId=" + blogId +
                ", parentCommentId=" + parentCommentId +
                ", parentNickname='" + parentNickname + '\'' +
                ", replyComments=" + replyComments +
                ", parentComment=" + parentComment +
                ", adminComment=" + adminComment +
                ", blog=" + blog +
                '}';
    }
}
