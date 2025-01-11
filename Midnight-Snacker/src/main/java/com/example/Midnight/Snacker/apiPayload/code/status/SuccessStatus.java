package com.example.Midnight.Snacker.apiPayload.code.status;

import com.example.Midnight.Snacker.apiPayload.code.BaseCode;
import com.example.Midnight.Snacker.apiPayload.code.ReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessStatus implements BaseCode {
    _OK(HttpStatus.OK, "COMMON200", "성공입니다."),

    //회원
    USER_LOGIN_OK(HttpStatus.OK, "AUTH2001", "회원 로그인이 완료되었습니다."),
    USER_DELETE_OK(HttpStatus.OK, "AUTH2002", "회원 탈퇴가 완료되었습니다."),
    USER_REFRESH_OK(HttpStatus.OK, "AUTH2003", "토큰 재발급이 완료되었습니다."),
    MYPAGE_OK(HttpStatus.OK, "AUTH2004", "마이페이지 조회가 완료되었습니다."),

    //Post
    POST_DELETE_OK(HttpStatus.OK, "POST2001", "게시글이 성공적으로 삭제되었습니다."),

    //Comment
    COMMENT_POST_OK(HttpStatus.OK, "COMMENT2001", "댓글이 성공적으로 달렸습니다.")
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ReasonDTO getReason() {
        return ReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(true)
                .build();
    }

    @Override
    public ReasonDTO getReasonHttpStatus() {
        return ReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(true)
                .httpStatus(httpStatus)
                .build()
                ;
    }
}
