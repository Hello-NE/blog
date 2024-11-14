package com.example.blog.po;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "t_essay")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
public class Essay {
    @Id
    @GeneratedValue
    private Long id;

    private Long praise;
    private String title;
    private String content;
    private String color;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @Override
    public String toString() {
        return "Essay{" +
                "id=" + id +
                ", praise=" + praise +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", color='" + color + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}