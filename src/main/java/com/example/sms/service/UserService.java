package com.example.sms.service;

import com.example.sms.dto.UserDto;

import java.util.List;

public interface UserService {
    void register(UserDto userDto) throws Exception;

    void login(UserDto userDto) throws Exception;

    List<String> fetchUsers(String username);
}
