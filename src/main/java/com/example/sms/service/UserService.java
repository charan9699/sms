package com.example.sms.service;

import com.example.sms.dto.UserDto;

public interface UserService {
    void registerUser(UserDto userDto) throws Exception;
}
