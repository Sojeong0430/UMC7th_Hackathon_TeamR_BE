package com.example.Midnight.Snacker.apiPayload.code;

import org.springframework.http.HttpStatus;

public interface BaseErrorCode {
    HttpStatus getHttpStatus();

    String getMessage();

    String getCode();

    ErrorReasonDTO getReason();

    ErrorReasonDTO getReasonHttpStatus();
}
