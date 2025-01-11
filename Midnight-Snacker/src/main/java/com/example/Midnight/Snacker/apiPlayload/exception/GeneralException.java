package com.example.Midnight.Snacker.apiPlayload.exception;

import com.example.Midnight.Snacker.apiPlayload.code.BaseErrorCode;
import com.example.Midnight.Snacker.apiPlayload.code.ErrorReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException{

    private BaseErrorCode code;

    public ErrorReasonDTO getErrorReason() {
        return this.code.getReason();
    }
    public ErrorReasonDTO getErrorReasonHttpStatus(){
        return this.code.getReasonHttpStatus();
    }

}
