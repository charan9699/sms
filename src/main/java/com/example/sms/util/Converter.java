package com.example.sms.util;

import com.example.sms.MessageStatus;
import com.example.sms.dto.MessageRequest;
import com.example.sms.dto.MessageResponse;
import com.example.sms.dto.UserDto;
import com.example.sms.entity.Message;
import com.example.sms.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;

public class Converter {
    public static User convertUserDtoToEntity(UserDto userDto) {
        User user = new User();
        //convert username to lowercase??
        user.setUsername(userDto.getUsername());
        user.setPasscode(userDto.getPasscode());
        user.setEmail(userDto.getEmail());
        return user;
    }

    public static Message convertToMessageEntity(MessageRequest messageRequest, User sender, User receiver) {
        Message message = new Message();
        message.setReceiver(receiver);
        message.setSender(sender);
        message.setText(messageRequest.getText());
        message.setStatus(MessageStatus.RECEIVED);
        return message;
    }

    public static List<MessageResponse> convertToMessageResponse(List<Message> messages) {
        if (messages == null) {
            return null;
        }
        List<MessageResponse> messageResponses = new ArrayList<>();
        for (Message message : messages) {
            MessageResponse messageResponse = new MessageResponse();
            messageResponse.setFrom(message.getSender().getUsername());
            messageResponse.setText(message.getText());
            messageResponse.setTimestamp(message.getCreated());
            messageResponses.add(messageResponse);
        }
        return messageResponses;
    }

    public static UserDetails convertUserEntityToUserDetails(User user) {
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPasscode(), new ArrayList<>());
    }
}
