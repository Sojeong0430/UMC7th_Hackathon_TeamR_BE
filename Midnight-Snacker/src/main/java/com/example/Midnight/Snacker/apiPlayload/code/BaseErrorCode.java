package com.example.Midnight.Snacker.apiPlayload.code;

import org.springframework.http.HttpStatus;

public interface BaseErrorCode {
    HttpStatus getHttpStatus();

    String getMessage();

    String getCode();

    ErrorReasonDTO getReason();

    ErrorReasonDTO getReasonHttpStatus();
}
