package com.example.sms.controller;

import com.example.sms.dto.MessageRequest;
import com.example.sms.dto.MessageResponse;
import com.example.sms.dto.Response;
import com.example.sms.service.MessageService;
import com.example.sms.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageController {
    @Autowired
    private MessageService messageService;

    @GetMapping("/user/{username}/message")
    public Response<?> fetchUnread(@PathVariable("username") String username){
        try {
            List<MessageResponse> messages = messageService.fetchUnreadMessages(username);
            if (null == messages) {
                return new Response<>("No new messages");
            }
            return new Response<>(messages);
        } catch (Exception e) {
            return new Response<>(Constants.Status.FAILURE, e.getMessage());
        }
    }

    @PostMapping("/user/{username}/message")
    public Response<?> sendMessage(@PathVariable("username") String username, @RequestBody MessageRequest messageRequest) {
        try {
            messageService.saveMessage(username, messageRequest);
            return new Response<>();
        } catch (Exception e) {
            return new Response<>(Constants.Status.FAILURE, e.getMessage());
        }
    }
}
