package com.example.Midnight.Snacker.web.dto.MemberDTO;

import lombok.*;

public class AuthResponseDTO {
    @Getter
    @Builder
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class OAuthResponse {
        Long memberId;
        String accessToken;
        String refreshToken;
    }

    @Getter
    @Builder
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class TokenRefreshResponse {
        String accessToken;
        String refreshToken;
    }
}
