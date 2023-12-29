package com.example.sms.service.impl;

import com.example.sms.dto.UserDto;
import com.example.sms.entity.User;
import com.example.sms.repository.UserRepository;
import com.example.sms.service.UserService;
import com.example.sms.util.Converter;
import com.example.sms.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void register(UserDto userDto) throws Exception {
        Validator.validateUserDto(userDto);
        if (userExists(userDto.getUsername())) {
            throw new Exception("User already exists");
        }
        User user = Converter.convertUserDtoToEntity(userDto);
        userRepository.save(user);
    }

    @Override
    public void login(UserDto userDto) throws Exception {
        Validator.validateUserDto(userDto);
        User user = userRepository.findByUsername(userDto.getUsername());
        if (null == user) {
            throw new Exception("User does not exist");
        }
        if (!user.getPasscode().equals(userDto.getPasscode())) {
            throw new Exception("Invalid passcode");
        }
    }

    private boolean userExists(String username) {
        return null != userRepository.findByUsername(username);
    }
}
