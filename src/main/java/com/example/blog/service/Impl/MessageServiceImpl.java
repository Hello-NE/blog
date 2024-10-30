package com.example.blog.service.Impl;

//import org.springframework.stereotype.Service;
import com.example.blog.dao.MessageRepository;
import com.example.blog.po.Message;
import com.example.blog.service.MessageService;
import com.example.blog.util.MyBeanUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class MessageServiceImpl implements MessageService{
    @Autowired
    private MessageRepository messageRepository;
    @Override
    public List<Message> listMessage() {
        return messageRepository.findAll();
    }

    @Override
    public void deleteMessage(Long id) {
        messageRepository.deleteById(id);
    }

    @Override
    public Message saveMessage(Message message) {
        message.setCreateTime(new Date());
        return messageRepository.save(message);
    }

    @Override
    public Message updateMessage(Long id, Message message) {
        Message m = messageRepository.getOne(id);
        BeanUtils.copyProperties(message,m, MyBeanUtils.getNullPropertyNames(message));
        return messageRepository.save(m);
    }
}