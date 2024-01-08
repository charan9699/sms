package com.example.sms.service.impl;

import com.example.sms.dto.UserDto;
import com.example.sms.entity.User;
import com.example.sms.repository.UserRepository;
import com.example.sms.service.UserService;
import com.example.sms.util.Converter;
import com.example.sms.util.Utility;
import com.example.sms.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public void register(UserDto userDto) throws Exception {
        Validator.validateUserDto(userDto);
        if (userExists(userDto.getUsername())) {
            throw new Exception("User already exists");
        }
        User user = Converter.convertUserDtoToEntity(userDto);
        user.setPasscode(passwordEncoder.encode(user.getPasscode()));
        userRepository.save(user);
    }

    @Override
    public String login(UserDto userDto) throws Exception {
        Validator.validateUserDto(userDto);
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPasscode()));
        } catch (AuthenticationException e) {
            throw new Exception("Invalid username or passcode");
        }
        return Utility.generateToken(userDto.getUsername());
    }

    @Override
    public List<String> fetchUsers(String username) {
        return userRepository.findAllUsersExcept(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (null == user) {
            throw new UsernameNotFoundException(String.format("User %s not found", username));
        }
        return Converter.convertUserEntityToUserDetails(user);
    }

    private boolean userExists(String username) {
        return null != userRepository.findByUsername(username);
    }
}
