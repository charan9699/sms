package com.example.sms.util;

import com.example.sms.dto.UserDto;
import org.springframework.util.StringUtils;

public class Validator {
    public static void validateUserDto(UserDto userDto) throws Exception {
        if (StringUtils.isEmpty(userDto.getUsername()) || StringUtils.isEmpty(userDto.getPasscode())) {
            throw new Exception("Username or passcode cannot be empty");
        }
    }

    public static void validateUsername(String username) throws Exception {
        if (StringUtils.isEmpty(username)) {
            throw new Exception("Invalid username");
        }
    }
}
