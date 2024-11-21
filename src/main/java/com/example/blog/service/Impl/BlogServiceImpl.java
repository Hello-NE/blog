package com.example.blog.service.Impl;

import com.example.blog.dao.BlogRepository;
import com.example.blog.dao.CommentRepository;
import com.example.blog.po.Blog;
import com.example.blog.po.Comment;
import com.example.blog.po.Type;
import com.example.blog.service.BlogService;
import com.example.blog.service.CommentService;
import com.example.blog.service.UserService;
import com.example.blog.util.MarkdownUtils;
import com.example.blog.util.MyBeanUtils;
import com.example.blog.vo.BlogQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;
import java.util.*;

@Service
public class BlogServiceImpl implements BlogService {


    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @Override
    public Blog getBlog(Long id) {
        return blogRepository.getOne(id);
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable, BlogQuery blog) {
        return blogRepository.findAll((Specification<Blog>) (root, cq, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
//            根据标题查找
            if (!"".equals(blog.getTitle()) && blog.getTitle() != null) {
                predicates.add(cb.like(root.get("title"), "%" + blog.getTitle() + "%"));
            }
//            根据type对象的id值查找
            if (blog.getTypeId() != null) {
                predicates.add(cb.equal(root.<Type>get("type").get("id"), blog.getTypeId()));
            }

            cq.where(predicates.toArray(new Predicate[predicates.size()]));
            return null;
        }, pageable);
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable) {
        Page<Blog> blogs = blogRepository.findAll(pageable);
        blogs.stream().forEach(blog -> {
            blog.setContent("");
            blog.setComments(null);
        });
        return blogs;
    }

    @Override
    public Page<Blog> listBlog(Long tagId, Pageable pageable) {
        Page<Blog> blogs = blogRepository.findAll((Specification<Blog>) (root, cq, cb) -> cb.equal(root.join("tags").get("id"), tagId), pageable);
        blogs.stream().forEach(blog -> {
            blog.setContent("");//不用知道文章的具体内容，只返回文章的基本信息就可以了
            blog.setComments(null);
        });
        return blogs;
    }

    @Override
    public Page<Blog> listBlog(String query, Pageable pageable) {
        Page<Blog> blogs = blogRepository.findByQuery(query, pageable);
        blogs.stream().forEach(blog -> {
            blog.setContent("");//不用知道文章的具体内容，只返回文章的基本信息就可以了
            blog.setComments(null);
        });
        return blogs;
    }

    @Override
    public List<Blog> listRecommendBlogTop(Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
        Pageable pageable = PageRequest.of(0, size, sort);
        List<Blog> blogs = blogRepository.findTop(pageable);
        blogs.stream().forEach(blog -> {
            blog.setContent("");//不用知道文章的具体内容，只返回文章的基本信息就可以了
            blog.setComments(null);
        });
        return blogs;
    }

    @Transactional
    @Override
    public Blog saveBlog(Blog blog) {
        if (blog.getFlag() == null || "".equals(blog.getFlag())) {
            blog.setFlag("原创");
        }
        if (blog.getFirstPicture() == null || "".equals(blog.getFirstPicture())) {
            blog.setFirstPicture(blog.getType().getPic_url());
        }
        if (blog.getDescription() == null || "".equals(blog.getDescription())) {
            blog.setDescription(blog.getContent().substring(0, Math.min(120, blog.getContent().length())));
        }
        if (blog.getId() == null) {
            blog.setCreateTime(new Date());
            blog.setUpdateTime(new Date());
            blog.setViews(0);
        } else {
            blog.setUpdateTime(new Date());
        }
        blog = blogRepository.save(blog);
        if (blog.getComments() != null) {
            for (Comment comment : blog.getComments()) {
                comment.setCreateTime(new Date());
                comment.setBlog(blog);
                comment.setAvatar(userService.findUserById(comment.getUserId())
                        .getAvatar());
                comment.setNickname(userService.findUserById(comment.getUserId())
                        .getNickname());
                comment.setEmail(userService.findUserById(comment.getUserId())
                        .getEmail());
                commentRepository.save(comment);
            }
        }
        return blog;
    }

    @Transactional
    @Override
    public Blog updateBlog(Long id, Blog blog) {
        Blog b = blogRepository.getOne(id);
        BeanUtils.copyProperties(blog, b, MyBeanUtils.getNullPropertyNames(blog));
        if (b.getFirstPicture() == null || "".equals(b.getFirstPicture())) {
            b.setFirstPicture(b.getType().getPic_url());
        }
        b.setUpdateTime(new Date());
        return blogRepository.save(b);
    }

    @Transactional
    @Override
    public void deleteBlog(Long id) {
        //blog_id

        blogRepository.deleteById(id);

    }

    @Override
    public Blog getAndConvert(Long id) {
        Blog blog = blogRepository.getOne(id);
        blog.setViews(blog.getViews() + 1);
        blog = blogRepository.save(blog);
        Blog b = new Blog();
        BeanUtils.copyProperties(blog, b);
        String content = b.getContent();
        b.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
        return b;
    }

    @Override
    public Map<String, List<Blog>> archiveBlog() {
        List<String> years = blogRepository.findGroupYear();
        Map<String, List<Blog>> map = new HashMap<>();
        for (String year : years) {
            map.put(year, blogRepository.findByYear(year));
        }
        return map;
    }

    @Override
    public Long countBlog() {
        return blogRepository.count();
    }

    @Override
    public Long countViews() {
        return blogRepository.countViews();
    }

    @Override
    public Long countAppreciate() {
        return blogRepository.countAppreciate();
    }

    @Override
    public Long countComment() {
        return blogRepository.countComment();
    }

    @Override
    public List<String> ViewCountByMonth() {
        return blogRepository.ViewCountByMonth();
    }

    @Override
    public List<String> BlogCountByMonth() {
        return blogRepository.BlogCountByMonth();
    }

    @Override
    public List<String> appreciateCountByMonth() {
        return blogRepository.appreciateCountByMonth();
    }
}
