package com.schmudlach.lagersystem.controller;

import com.schmudlach.lagersystem.error.ApiError;
import com.schmudlach.lagersystem.error.BadRequestException;
import com.schmudlach.lagersystem.error.ConflictException;
import com.schmudlach.lagersystem.error.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;

@RestControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiError> handleNotFound(NotFoundException ex,
                                                   HttpServletRequest request) {
        ApiError body = new ApiError(
                HttpStatus.NOT_FOUND.value(),
                "Not Found",
                ex.getMessage(),
                request.getRequestURI(),
                Arrays.toString(ex.getStackTrace())
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }


    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiError> handleNotFound(BadRequestException ex,
                                                   HttpServletRequest request) {
        ApiError body = new ApiError(
                HttpStatus.BAD_REQUEST.value(),
                "Bad Request",
                ex.getMessage(),
                request.getRequestURI(),
                Arrays.toString(ex.getStackTrace())
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ApiError> handleConflictException(ConflictException ex,
                                                   HttpServletRequest request) {
        ApiError body = new ApiError(
                HttpStatus.CONFLICT.value(),
                "Conflict exeption",
                ex.getMessage(),
                request.getRequestURI(),
                Arrays.toString(ex.getStackTrace())
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
    }
}
