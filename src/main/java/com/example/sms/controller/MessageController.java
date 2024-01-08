package com.example.sms.controller;

import com.example.sms.dto.MessageRequest;
import com.example.sms.dto.MessageResponse;
import com.example.sms.dto.Response;
import com.example.sms.service.MessageService;
import com.example.sms.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class MessageController {
    @Autowired
    private MessageService messageService;

    @GetMapping("/user/{username}/message")
    public Response<?> fetchUnread(@RequestParam(name = "friend", required = false) String friend,
                                   HttpServletRequest request) {
        try {
            String username = getUsernameFromAuth();
            if (null != friend) {
                List<MessageResponse> unreadMessagesFromFriend = messageService.fetchUnreadMessages(username, friend);
                if (null != unreadMessagesFromFriend) {
                    return new Response<>(Constants.Status.SUCCESS, "You have new message(s)", unreadMessagesFromFriend);
                }
                return fetchChatHistory(username, friend);
            }
            List<MessageResponse> allUnreadMessages = messageService.fetchUnreadMessages(username, null);
            if (null == allUnreadMessages) {
                return new Response<>("No new messages");
            }
            return new Response<>(Constants.Status.SUCCESS, "You have new message(s)", allUnreadMessages);
        } catch (Exception e) {
            return new Response<>(Constants.Status.FAILURE, e.getMessage());
        }
    }

    @PostMapping("/user/{username}/message")
    public Response<?> sendMessage(@RequestBody MessageRequest messageRequest) {
        try {
            messageService.saveMessage(getUsernameFromAuth(), messageRequest);
            return new Response<>();
        } catch (Exception e) {
            return new Response<>(Constants.Status.FAILURE, e.getMessage());
        }
    }

    private Response<?> fetchChatHistory(String username, String friend){
        //todo: implement pagination and limit
        try {
            List<MessageResponse> messages = messageService.fetchChatHistory(username, friend);
            if (null == messages) {
                return new Response<>("No messages");
            }
            return new Response<>(Constants.Status.SUCCESS, "Chat History", messages);
        } catch (Exception e) {
            return new Response<>(Constants.Status.FAILURE, e.getMessage());
        }
    }

    private String getUsernameFromAuth() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return String.valueOf(authentication.getPrincipal());
    }
}
