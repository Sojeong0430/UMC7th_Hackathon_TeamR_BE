package com.example.Midnight.Snacker.web.controller;

import com.example.Midnight.Snacker.apiPayload.ApiResponse;
import com.example.Midnight.Snacker.service.MemberService.MemberService;
import com.example.Midnight.Snacker.web.dto.MemberDTO.AuthResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.example.Midnight.Snacker.apiPayload.code.status.SuccessStatus;
import com.example.Midnight.Snacker.domain.Member;
import com.example.Midnight.Snacker.security.handler.annotation.AuthUser;
import com.example.Midnight.Snacker.web.dto.MemberDTO.AuthRequestDTO;
import com.example.Midnight.Snacker.web.dto.MemberDTO.AuthResponseDTO;
import com.example.Midnight.Snacker.web.dto.MemberDTO.MemberResponseDTO;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "카카오 로그인 API", description = "카카오 로그인 및 회원 가입을 진행하는 API입니다.")
    @GetMapping("/login")
    public ApiResponse<AuthResponseDTO.OAuthResponse> kakaoLogin(@RequestParam("code") String code) {
        return ApiResponse.of(SuccessStatus.USER_LOGIN_OK, memberService.kakaoLogin(code));
    }

    @ResponseStatus(code = HttpStatus.OK)
    @Operation(
            summary = "JWT Access Token 재발급 API",
            description = "Refresh Token을 검증하고 새로운 Access Token과 Refresh Token을 응답합니다.")
    @PostMapping("/refresh")
    public ApiResponse<AuthResponseDTO.TokenRefreshResponse> refresh(@RequestBody AuthRequestDTO.RefreshTokenDTO request) {
        return ApiResponse.of(SuccessStatus.USER_REFRESH_OK, memberService.refresh(request.getRefreshToken()));
    }

    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "마이페이지 API", description = "마이페이지의 회원 정보를 가져오는 API입니다.")
    @GetMapping("/mypage")
    public ApiResponse<MemberResponseDTO.MyPageResponse> getMyPageInfo(@Parameter(name = "user", hidden = true) @AuthUser Member member) {
        return ApiResponse.of(SuccessStatus.MYPAGE_OK, memberService.getMyPageInfo(member));
    }
}
