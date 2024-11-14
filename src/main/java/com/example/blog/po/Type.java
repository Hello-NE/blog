package com.example.blog.po;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@Entity
@Table(name = "t_type")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
public class Type {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String pic_url;

    private String color;

    @OneToMany(mappedBy = "type")
    private List<Blog> blogs = new ArrayList<>();

    public Type() {

    }

    @Override
    public String toString() {
        return "Type{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pic_url='" + pic_url + '\'' +
                ", color='" + color + '\'' +
//                ", blogs=" + blogs +
                '}';
    }
}
