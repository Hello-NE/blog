package com.example.blog.service;

import com.example.blog.pojo.Type;

import java.util.List;

public interface TypeService {

    List<Type> listTypes();

    Type getType(Long typeId);

    List<Type> getAlltype();

    int saveType(Type type);

    Type getTypeByName(String name);

    int updateType(Type type);

    int deleteById(Long id);

    List<Type> listTypesAndBlogs();
}
