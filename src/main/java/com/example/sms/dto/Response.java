package com.example.sms.dto;

import com.example.sms.util.Constants;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {
    private String status;
    private String message;
    private T data;

    public Response(T data) {
        this.status = Constants.Status.SUCCESS;
        this.data = data;
    }

    public Response(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public Response() {
        this.status = Constants.Status.SUCCESS;
    }
}
