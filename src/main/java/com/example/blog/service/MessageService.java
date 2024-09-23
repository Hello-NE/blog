package com.example.blog.service;

import com.example.blog.pojo.Message;

import java.util.List;

public interface MessageService {
    List<Message> listMessages();

    void saveMessage(Message message);

    void deleteMessage(Long id);
}

