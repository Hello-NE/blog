package com.example.blog.service;

import com.example.blog.po.Message;

import java.util.List;

public interface MessageService  {

    List<Message> listMessage();

    void deleteMessage(Long id);

    Message saveMessage(Message message);

    Message updateMessage(Long id,Message message);
}
