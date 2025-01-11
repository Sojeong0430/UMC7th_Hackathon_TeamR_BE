package com.example.Midnight.Snacker.apiPayload.exception;

import com.example.Midnight.Snacker.apiPayload.code.BaseErrorCode;

public class AuthException extends GeneralException {

    public AuthException(BaseErrorCode code) {
        super(code);
    }
}
