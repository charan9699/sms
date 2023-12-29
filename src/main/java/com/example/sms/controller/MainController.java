package com.example.sms.controller;

import com.example.sms.dto.Response;
import com.example.sms.dto.UserDto;
import com.example.sms.service.UserService;
import com.example.sms.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
public class MainController {

    @Autowired
    private UserService userService;

    @GetMapping("/health")
    public Response<?> health() {
        return new Response<>();
    }

    @PostMapping("/user")
    public Response<?> registerUser(@RequestBody UserDto userDto) {
        try {
            userService.registerUser(userDto);
            return new Response<>(Constants.Status.SUCCESS, "User created successfully");
        } catch (Exception e) {
            return new Response<>(Constants.Status.FAILURE, e.getMessage());
        }
    }

    @GetMapping("/user")
    public Response<?> getUsers() {
        return new Response<>(Arrays.asList("abc","def","ghi"));
    }
}
