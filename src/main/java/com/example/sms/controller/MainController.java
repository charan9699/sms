package com.example.sms.controller;

import com.example.sms.dto.Response;
import com.example.sms.util.Constants;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
public class MainController {

    @GetMapping("/health")
    public Response<?> health() {
        return new Response<>();
    }

    @PostMapping("/user")
    public Response<?> registerUser() {
        return new Response<>(Constants.Status.SUCCESS, "User created successfully");
    }

    @GetMapping("/user")
    public Response<?> getUsers() {
        return new Response<>(Arrays.asList("abc","def","ghi"));
    }
}
