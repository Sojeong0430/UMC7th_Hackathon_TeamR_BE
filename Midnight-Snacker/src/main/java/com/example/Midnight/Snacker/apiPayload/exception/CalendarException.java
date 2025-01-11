package com.example.Midnight.Snacker.apiPayload.exception;

import com.example.Midnight.Snacker.apiPayload.code.BaseErrorCode;

public class CalendarException extends GeneralException {

    public CalendarException(BaseErrorCode code) {
        super(code);
    }
}
