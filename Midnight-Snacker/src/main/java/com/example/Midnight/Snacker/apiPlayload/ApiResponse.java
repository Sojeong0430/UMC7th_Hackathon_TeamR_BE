package com.example.Midnight.Snacker.apiPlayload;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import com.example.Midnight.Snacker.apiPlayload.code.status.SuccessStatus;
import com.example.Midnight.Snacker.apiPlayload.code.BaseCode;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess","code","message","result"})
public class ApiResponse<T> {

    @JsonProperty("isSuccess")
    private final Boolean isSuccess; //응답 성공 여부를 나타냄
    private final String code; //응답 코드
    private final String message; //응답 메세지
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T result; //응답 데이터, NULL이 아닐때만 Json에 포함


    // 성공한 경우 응답 객체 생성
    public static <T> ApiResponse<T> onSuccess(T result){
        return new ApiResponse<>(true, SuccessStatus._OK.getCode() , SuccessStatus._OK.getMessage(), result);
    }

    public static <T> ApiResponse<T> of(BaseCode code, T result){
        return new ApiResponse<>(true, code.getReasonHttpStatus().getCode() , code.getReasonHttpStatus().getMessage(), result);
    }


    // 실패한 경우 응답 생성
    public static <T> ApiResponse<T> onFailure(String code, String message, T data){
        return new ApiResponse<>(false, code, message, data);
    }
}
