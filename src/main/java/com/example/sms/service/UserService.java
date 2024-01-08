package com.example.sms.service;

import com.example.sms.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {
    void register(UserDto userDto) throws Exception;

    String login(UserDto userDto) throws Exception;

    List<String> fetchUsers(String username);

    UserDetails loadUserByUsername(String username);
}
