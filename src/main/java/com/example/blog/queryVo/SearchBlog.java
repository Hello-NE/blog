package com.example.blog.queryVo;

import lombok.Data;

@Data
public class SearchBlog {

    private String title;
    private Long typeId;

    @Override
    public String toString() {
        return "SearchBlog{" +
                "title='" + title + '\'' +
                ", typeId=" + typeId +
                '}';
    }

}
