package com.example.blog.queryVo;

import lombok.Data;

import java.util.Date;

@Data
public class ArchiveBlog {
    private Long id;
    private String title;
    private Date createTime;

    @Override
    public String toString() {
        return "ArchiveBlog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", createTime=" + createTime +
                '}';
    }


}
