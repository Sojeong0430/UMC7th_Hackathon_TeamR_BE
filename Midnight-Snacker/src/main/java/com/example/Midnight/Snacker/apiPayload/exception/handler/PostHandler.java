package com.example.Midnight.Snacker.apiPayload.exception.handler;

import com.example.Midnight.Snacker.apiPayload.code.BaseErrorCode;
import com.example.Midnight.Snacker.apiPayload.exception.GeneralException;

public class PostHandler extends GeneralException {
    public PostHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
