package com.example.Midnight.Snacker.converter;

import com.example.Midnight.Snacker.domain.Member;
import com.example.Midnight.Snacker.domain.enums.Status;
import com.example.Midnight.Snacker.web.dto.MemberDTO.AuthResponseDTO;
import com.example.Midnight.Snacker.web.dto.authDTO.KakaoProfile;

public class AuthConverter {

    public static Member toMember(KakaoProfile kakaoProfile) {
        return Member.builder()
                .nickname(kakaoProfile.getKakaoNickname().getNickname())
                .email(kakaoProfile.getKakaoAccount().getEmail())
                .status(Status.ACTIVE)
                .build();
    }

    public static AuthResponseDTO.OAuthResponse toOAuthResponse(
            String accessToken, String refreshToken, Member member) {
        return AuthResponseDTO.OAuthResponse.builder()
                .refreshToken(refreshToken)
                .accessToken(accessToken)
                .memberId(member.getId())
                .build();
    }

    public static AuthResponseDTO.TokenRefreshResponse toTokenRefreshResponse(
            String accessToken, String refreshToken) {
        return AuthResponseDTO.TokenRefreshResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
