package com.example.blog.po;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Entity
@Table(name = "t_tag")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
public class Tag {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "tags", cascade = {CascadeType.PERSIST})
    private List<Blog> blogs = new ArrayList<>();

    public Tag() {
    }

    @JsonIgnoreProperties({"tags"})
    public List<Blog> getBlogs() {
        return blogs;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}