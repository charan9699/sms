package com.example.sms.controller;

import com.example.sms.dto.Response;
import com.example.sms.dto.UserDto;
import com.example.sms.service.UserService;
import com.example.sms.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
            userService.register(userDto);
            return new Response<>(Constants.Status.SUCCESS, "User created successfully");
        } catch (Exception e) {
            return new Response<>(Constants.Status.FAILURE, e.getMessage());
        }
    }

    //todo: modify to session based login
    @PostMapping("/login")
    public Response<?> loginUser(@RequestBody UserDto userDto) {
        try {
            userService.login(userDto);
            return new Response<>();
        } catch (Exception e) {
            return new Response<>(Constants.Status.FAILURE, e.getMessage());
        }
    }

    @GetMapping("/user/{username}")//todo: modify to get username from session
    public Response<?> getUsers(@PathVariable("username") String username){
        try {
            return new Response<>(userService.fetchUsers(username));
        } catch (Exception e) {
            return new Response<>(Constants.Status.FAILURE, e.getMessage());
        }
    }
}
