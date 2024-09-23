package com.example.blog.service;

import com.example.blog.pojo.Picture;

import java.util.List;

public interface PictureService {
    List<Picture> listPictures();

    int savePicture(Picture picture);

    Picture getPictureById(Long id);

    int updatePicture(Picture picture);

    void deletePicture(Long id);
}
