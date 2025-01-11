package com.example.Midnight.Snacker.service.MemberService;

import com.example.Midnight.Snacker.domain.Member;
import com.example.Midnight.Snacker.web.dto.MemberDTO.AuthResponseDTO;
import com.example.Midnight.Snacker.web.dto.MemberDTO.MemberResponseDTO;

public interface MemberService {
    Member findMemberById(Long memberId);

    AuthResponseDTO.OAuthResponse kakaoLogin(String code);

    MemberResponseDTO.MyPageResponse getMyPageInfo(Member member);

    AuthResponseDTO.TokenRefreshResponse refresh(String refreshToken);
}
