package com.example.blog.po;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@Table(name = "t_project")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
public class Project {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String content;
    private String pic_url;
    private String url;
    private String techs;
    private Integer type;

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", pic_url='" + pic_url + '\'' +
                ", url='" + url + '\'' +
                ", techs='" + techs + '\'' +
                ", type=" + type +
                '}';
    }
}
