package com.example.Midnight.Snacker.apiPayload.exception.handler;

import com.example.Midnight.Snacker.apiPayload.code.BaseErrorCode;
import com.example.Midnight.Snacker.apiPayload.exception.GeneralException;

public class CommentHandler extends GeneralException {
    public CommentHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
