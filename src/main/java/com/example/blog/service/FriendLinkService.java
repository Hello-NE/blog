package com.example.blog.service;

import com.example.blog.pojo.FriendLink;

import java.util.List;

public interface FriendLinkService {

    List<FriendLink> listFriendLike();

    FriendLink getFriendLink(String blogaddress);


    int saveFriendLink(FriendLink friendLink);

    FriendLink getFriendLinkById(Long id);

    int updateFriendLink(FriendLink friendLink);

    void deleteFriendLink(Long id);
}
