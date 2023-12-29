package com.example.sms.service;

import com.example.sms.dto.MessageRequest;
import com.example.sms.dto.MessageResponse;

import java.util.List;

public interface MessageService {
    List<MessageResponse> fetchUnreadMessages(String username) throws Exception;

    void saveMessage(String username, MessageRequest message) throws Exception;

    List<MessageResponse> fetchChatHistory(String username, String friend);
}
