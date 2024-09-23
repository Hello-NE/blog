package com.example.blog.service;


import com.example.blog.pojo.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> listCommentByBlogId(Long id);

    void saveComment(Comment comment);

    void deleteComment(Comment comment, Long id);
}
