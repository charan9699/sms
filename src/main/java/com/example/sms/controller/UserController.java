package com.example.sms.controller;

import com.example.sms.dto.Response;
import com.example.sms.dto.UserDto;
import com.example.sms.service.UserService;
import com.example.sms.util.Constants;
import com.example.sms.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class UserController {

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

    @PostMapping("/login")
    public Response<?> loginUser(@RequestBody UserDto userDto, HttpServletResponse response) {
        try {
            String authToken = userService.login(userDto);
            if (null != authToken) {
                response.addCookie(Utility.getCookies(authToken));
                return new Response<>(Constants.Status.SUCCESS, "Login successful");
            }
            return new Response<>(Constants.Status.FAILURE, "Login failed");
        } catch (Exception e) {
            return new Response<>(Constants.Status.FAILURE, e.getMessage());
        }
    }

    @GetMapping("/user")
    public Response<?> getUsers(HttpServletRequest request) {
        try {
            String username = Utility.extractUsernameFromCookies(request.getCookies());
            return new Response<>(userService.fetchUsers(username));
        } catch (Exception e) {
            return new Response<>(Constants.Status.FAILURE, e.getMessage());
        }
    }
}
