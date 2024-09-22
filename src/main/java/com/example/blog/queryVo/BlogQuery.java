package com.example.blog.queryVo;


import com.example.blog.pojo.Type;
import lombok.Data;

@Data
public class BlogQuery {

    private Long id;
    private String title;
    private String updateTime;
    private Boolean recommend;
    private Boolean published;
    private Long typeId;
    private Type type;

    @Override
    public String toString() {
        return "BlogQuery{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", recommend=" + recommend +
                ", published=" + published +
                ", typeId=" + typeId +
                ", type=" + type +
                '}';
    }

}
