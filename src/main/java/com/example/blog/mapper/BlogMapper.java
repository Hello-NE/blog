package com.example.blog.mapper;

import com.example.blog.pojo.Blog;
import com.example.blog.queryVo.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BlogMapper {

    List<BlogQuery> listBlogs();

    int saveBlog(Blog blog);

    ShowBlog getBolgById(Long id);

    int updateBlog(ShowBlog showBlog);

    int deleteBlogById(Long id);

    List<SearchBlog> searchBlogs(SearchBlog searchBlog);

    List<FirstPageBlog> getFirstPageBlogs();

    List<RecommendBlog> recommendedBlogs();

    List<FirstPageBlog> getSearchBlogs(String query);

    DetailedBlog getDetailedBlogById(Long id);

    void updateViews(Long id);

    void getCommentCountById(Long id);

    Integer getBlogTotal();

    Integer getBlogViewTotal();

    Integer getBlogCommentTotal();

    Integer getBlogMessageTotal();

    List<FirstPageBlog> getBlogsByTypeId(Long id);

    List<ArchiveBlog> getArchiveBlogs();
}
