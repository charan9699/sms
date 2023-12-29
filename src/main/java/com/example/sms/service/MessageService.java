package com.example.sms.service;

import java.util.List;

public interface MessageService {
    List<String> fetchUnreadMessages(String username);
}
