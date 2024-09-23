package com.example.blog.service;

import com.example.blog.pojo.Blog;
import com.example.blog.queryVo.*;

import java.util.List;

public interface BlogService {

    List<BlogQuery> listBlogs();

    int saveBlog(Blog blog);

    ShowBlog getBlogById(Long id);

    int updateBlog(ShowBlog showBlog);

    int deleteBlogById(Long id);

    List<SearchBlog> searchBlogs(SearchBlog searchBlog);

    List<FirstPageBlog> getFirstPageBlogs();

    List<RecommendBlog> recommendedBlogs();

    List<FirstPageBlog> getSearchBlogs(String query);

    DetailedBlog getDetailedBlogById(Long id);

    Integer getBlogTotal();

    Integer getBlogViewTotal();

    Integer getBlogCommentTotal();

    Integer getBlogMessageTotal();

    List<FirstPageBlog> getBlogsByTypeId(Long id);

    List<ArchiveBlog> getArchiveBlogs();
}
