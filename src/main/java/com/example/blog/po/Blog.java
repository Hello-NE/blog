package com.example.blog.po;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "t_blog")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
public class Blog {
    @Id
    @GeneratedValue
    private Long id;


    private String title;

    private String content;
    private String firstPicture;
    private String flag;
    private Integer views;
    private Integer appreciation;
    private boolean shareStatement;
    private boolean commentabled;
    private boolean published;
    private boolean recommend;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    private String description;

    @ManyToOne
    private Type type;
    //        级联关系----添加博客时自动添加标签
    @ManyToMany(cascade = {CascadeType.PERSIST})
    private List<Tag> tags = new ArrayList<>();

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "blog")
    @JsonManagedReference
    private List<Comment> comments = new ArrayList<>();

    @Transient
    private String tagIds;

    public Blog() {
    }

    public void init() {
        this.tagIds = tagsToIds(this.getTags());
    }

    public String getDescription() {
        return description;
    }

    public String getTagIds() {
        return tagIds;
    }

    @JsonIgnoreProperties({"blog"})
    public List<Comment> getComments() {
        return comments;
    }

    @JsonIgnoreProperties({"blogs"})
    public User getUser() {
        return user;
    }

    @JsonIgnoreProperties({"blogs"})
    public List<Tag> getTags() {
        return tags;
    }

    @JsonIgnoreProperties({"blogs"})
    public Type getType() {
        return type;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getFirstPicture() {
        return firstPicture;
    }

    public String getFlag() {
        return flag;
    }

    public Integer getViews() {
        return views;
    }

    public Integer getAppreciation() {
        return appreciation;
    }

    public boolean isShareStatement() {
        return shareStatement;
    }

    public boolean isCommentabled() {
        return commentabled;
    }

    public boolean isPublished() {
        return published;
    }

    public boolean isRecommend() {
        return recommend;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    private String tagsToIds(List<Tag> tags) {
        if (!tags.isEmpty()) {
            StringBuffer ids = new StringBuffer();
            boolean flag = false;
            for (Tag tag : tags) {
                if (flag) {
                    ids.append(",");
                } else {
                    flag = true;
                }
                ids.append(tag.getId());
            }
            return ids.toString();
        } else {
            return tagIds;
        }
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", firstPicture='" + firstPicture + '\'' +
                ", flag='" + flag + '\'' +
                ", views=" + views +
                ", appreciation=" + appreciation +
                ", shareStatement=" + shareStatement +
                ", commentabled=" + commentabled +
                ", published=" + published +
                ", recommend=" + recommend +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", description='" + description + '\'' +
                ", type=" + type +
//                ", tags=" + tags +
                ", user=" + user +
//                ", comments=" + comments +
                ", tagIds='" + tagIds + '\'' +
                '}';
    }

}
