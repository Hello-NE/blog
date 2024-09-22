package com.example.blog.mapper;

import com.example.blog.pojo.FriendLink;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MessageMapper {
    List<FriendLink> listFriendLike();

    FriendLink getFriendLink(String blogaddress);

    int saveFriendLink(FriendLink friendLink);

    FriendLink getFriendLinkById(Long id);

    int updateFriendLink(FriendLink friendLink);

    void deleteFriendLink(Long id);
}
