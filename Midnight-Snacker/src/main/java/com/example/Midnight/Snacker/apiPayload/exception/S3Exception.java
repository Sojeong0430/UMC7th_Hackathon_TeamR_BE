package com.example.Midnight.Snacker.apiPayload.exception;

import com.example.Midnight.Snacker.apiPayload.code.BaseErrorCode;

public class S3Exception extends GeneralException {

    public S3Exception(BaseErrorCode code) {
        super(code);
    }
}

