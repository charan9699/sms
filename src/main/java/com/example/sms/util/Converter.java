package com.example.sms.util;

import com.example.sms.dto.UserDto;
import com.example.sms.entity.User;

public class Converter {
    public static User convertUserDtoToEntity(UserDto userDto) {
        User user = new User();
        //convert username to lowercase??
        user.setUsername(userDto.getUsername());
        //convert passcode to md5??
        user.setPasscode(userDto.getPasscode());
        user.setEmail(userDto.getEmail());
        return user;
    }
}
