package com.example.sms.service.impl;

import com.example.sms.repository.MessageRepository;
import com.example.sms.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Override
    public List<String> fetchUnreadMessages(String username) {
        List<String> messages =  messageRepository.findAllUnreadMessagesByReceiver(username);
        if (CollectionUtils.isEmpty(messages)) {
            return null;
        }
        return messages;
    }
}
