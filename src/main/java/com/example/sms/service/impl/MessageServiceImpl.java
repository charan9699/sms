package com.example.sms.service.impl;

import com.example.sms.MessageStatus;
import com.example.sms.dto.MessageRequest;
import com.example.sms.dto.MessageResponse;
import com.example.sms.entity.Message;
import com.example.sms.entity.User;
import com.example.sms.repository.MessageRepository;
import com.example.sms.repository.UserRepository;
import com.example.sms.service.MessageService;
import com.example.sms.util.Converter;
import com.example.sms.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    public List<MessageResponse> fetchUnreadMessages(String username, String friend) throws Exception {
        Validator.validateUsername(username);
        List<Message> messages = null;
        if (null == friend) {
            messages = messageRepository.findByReceiver_usernameAndStatusOrderByCreatedDesc(username, MessageStatus.RECEIVED);
        } else {
            messages = messageRepository.findByReceiver_usernameAndSender_usernameAndStatusOrderByCreatedDesc(username, friend, MessageStatus.RECEIVED);
        }
        if (CollectionUtils.isEmpty(messages)) {
            return null;
        }
        messageRepository.updateStatus(messages, MessageStatus.READ);
        return Converter.convertToMessageResponse(messages);
    }

    @Override
    public void saveMessage(String username, MessageRequest messageRequest) throws Exception {
        Validator.validateMessageRequest(messageRequest);
        User sender = userRepository.findByUsername(username);
        User receiver = userRepository.findByUsername(messageRequest.getTo());
        if (null == sender || null == receiver) {
            throw new Exception("Invalid receiver or sender");
            //todo: create custom exceptions
        }
        Message message = Converter.convertToMessageEntity(messageRequest, sender, receiver);
        messageRepository.save(message);
    }

    @Override
    public List<MessageResponse> fetchChatHistory(String username, String friend) {
        List<Message> messages = messageRepository.findAllMessagesBetweenUsers(username, friend);
        if (CollectionUtils.isEmpty(messages)) {
            return null;
        }
        return Converter.convertToMessageResponse(messages);
    }
}
