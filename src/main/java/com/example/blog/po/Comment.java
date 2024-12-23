package com.example.blog.po;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_comment")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
public class Comment {

    @Id
    @GeneratedValue
    private Long id;
    private Long userId;
    private String nickname;
    private String email;

    private String content;
    private String avatar;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    //    @ManyToOne
    @ManyToOne(cascade = CascadeType.ALL)  // 使用级联操作
    private Blog blog = new Blog();

//    @OneToMany(mappedBy = "parentComment")
//    private List<Comment> replyComments = new ArrayList<>();

    @ManyToOne
    private Comment parentComment;

    private boolean adminComment;

    public Comment() {
    }

//    @JsonIgnore
//    public List<Comment> getReplyComments() {
//        return replyComments;
//    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

//    public void setReplyComments(List<Comment> replyComments) {
//        this.replyComments = replyComments;
//    }

    public Comment getParentComment() {
        return parentComment;
    }

    public void setParentComment(Comment parentComment) {
        this.parentComment = parentComment;
    }

    @JsonIgnoreProperties({"comments"})
    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avater) {
        this.avatar = avater;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public boolean isAdminComment() {
        return adminComment;
    }

    public void setAdminComment(boolean b) {
        this.adminComment = b;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", content='" + content + '\'' +
                ", avatar='" + avatar + '\'' +
                ", createTime=" + createTime +
//                ", blog=" + blog +
//                ", replyComments=" + replyComments +
//                ", parentComment=" + parentComment +
                ", adminComment=" + adminComment +
                '}';
    }
}