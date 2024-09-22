package com.example.blog.pojo;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Message {

    private Long id;
    private String email;
    private String nickname;
    private String content;
    private String avatar;
    private Date createTime;
    private Long parentMessageId;
    private boolean adminMessage;

    private List<Message> replyMessages = new ArrayList<>();
    private Message parentMessage;
    private String parentNickname;

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", nickname='" + nickname + '\'' +
                ", content='" + content + '\'' +
                ", avatar='" + avatar + '\'' +
                ", createTime=" + createTime +
                ", parentMessageId=" + parentMessageId +
                ", adminMessage=" + adminMessage +
                ", replyMessages=" + replyMessages +
                ", parentMessage=" + parentMessage +
                ", parentNickname='" + parentNickname + '\'' +
                '}';
    }
}
