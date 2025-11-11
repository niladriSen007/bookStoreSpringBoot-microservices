package com.niladri.catalogue_service.web.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;


public class ApiError {
    private LocalDateTime timestamp;
    private String message;
    private HttpStatus statusCode;

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public ApiError() {
        this.timestamp = LocalDateTime.now();
    }

    public ApiError(String message, HttpStatus statusCode) {
        this();
        this.message = message;
        this.statusCode = statusCode;
    }
}
