package com.example.blog.vo;

import lombok.Data;


@Data
public class BlogQuery {
    private String title;
    private Long typeId;

    public BlogQuery() {
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }


    @Override
    public String toString() {
        return "BlogQuery{" +
                "title='" + title + '\'' +
                ", typeId=" + typeId +
                '}';
    }
}
