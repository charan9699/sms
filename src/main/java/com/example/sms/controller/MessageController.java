package com.example.sms.controller;

import com.example.sms.dto.Response;
import com.example.sms.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageController {
    @Autowired
    private MessageService messageService;

    @GetMapping("/user/{username}/message")
    public Response<?> getMessages(@PathVariable("username") String username){
        List<String> messages = messageService.fetchUnreadMessages(username);
        if (null == messages) {
            return new Response<>("No new messages");
        }
        return new Response<>(messages);
    }
}
