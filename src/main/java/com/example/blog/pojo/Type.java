package com.example.blog.pojo;

import java.util.ArrayList;
import java.util.List;

public class Type {

    private Long id;
    private String name;

    private List<Blog> blogs = new ArrayList<>();

    @Override
    public String toString() {
        return "Type{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", blogs=" + blogs +
                '}';
    }


}
