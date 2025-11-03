package com.schmudlach.lagersystem.error;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ApiError {
    private final LocalDateTime timestamp = LocalDateTime.now();
    private final int status;
    private final String error;
    private final String message;
    private final String path;
    private final String staceTrace;

    public ApiError(int status, String error, String message, String path,String staceTrace) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
        this.staceTrace=staceTrace;
    }
}
