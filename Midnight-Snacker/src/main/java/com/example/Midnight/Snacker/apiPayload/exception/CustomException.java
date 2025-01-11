package com.example.Midnight.Snacker.apiPayload.exception;

import com.example.Midnight.Snacker.apiPayload.code.status.ErrorStatus;
import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException {
    private final ErrorStatus errorStatus;

    public CustomException(ErrorStatus errorStatus) {
        super(errorStatus.getMessage());
        this.errorStatus = errorStatus;
    }

    public HttpStatus getHttpStatus() {
        return errorStatus.getHttpStatus();
    }

    public String getErrorCode() {
        return errorStatus.getCode();
    }
}
