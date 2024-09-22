package com.example.blog.mapper;

import com.example.blog.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    User checkUsernameAndPassword(@Param("username") String username, @Param("password") String password);

}