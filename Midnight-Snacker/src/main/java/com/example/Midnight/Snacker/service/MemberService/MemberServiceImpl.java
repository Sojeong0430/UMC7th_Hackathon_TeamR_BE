package com.example.Midnight.Snacker.service.MemberService;

import com.example.Midnight.Snacker.apiPayload.code.status.ErrorStatus;
import com.example.Midnight.Snacker.apiPayload.exception.AuthException;
import com.example.Midnight.Snacker.converter.AuthConverter;
import com.example.Midnight.Snacker.converter.MemberConverter;
import com.example.Midnight.Snacker.domain.Member;
import com.example.Midnight.Snacker.domain.enums.Color;
import com.example.Midnight.Snacker.repository.CalendarRepository;
import com.example.Midnight.Snacker.repository.MemberRepository;
import com.example.Midnight.Snacker.repository.PostRepository;
import com.example.Midnight.Snacker.security.provider.JwtTokenProvider;
import com.example.Midnight.Snacker.security.provider.KakaoAuthProvider;
import com.example.Midnight.Snacker.web.dto.MemberDTO.AuthResponseDTO;
import com.example.Midnight.Snacker.web.dto.MemberDTO.MemberResponseDTO;
import com.example.Midnight.Snacker.web.dto.authDTO.KakaoProfile;
import com.example.Midnight.Snacker.web.dto.authDTO.OAuthToken;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final KakaoAuthProvider kakaoAuthProvider;
    private final CalendarRepository calendarRepository;


    @Override
    public Member findMemberById(Long memberId) {
        return memberRepository
                .findById(memberId)
                .orElseThrow(() -> new AuthException(ErrorStatus.USER_NOT_FOUND));
    }

    @Override
    @Transactional
    public AuthResponseDTO.OAuthResponse kakaoLogin(String code) {
        OAuthToken oAuthToken;
        try {
            oAuthToken = kakaoAuthProvider.requestToken(code);
        } catch (Exception e){
            throw new AuthException(ErrorStatus.AUTH_INVALID_CODE);
        }

        KakaoProfile kakaoProfile;
        try {
            kakaoProfile =
                    kakaoAuthProvider.requestKakaoProfile(oAuthToken.getAccess_token());
        }catch (Exception e){
            throw new AuthException(ErrorStatus.INVALID_REQUEST_INFO);
        }

        // 유저 정보 받기
        Optional<Member> queryMember =
                memberRepository.findByEmail(
                        kakaoProfile.getKakaoAccount().getEmail());

        // 가입자 혹은 비가입자 체크해서 로그인 처리
        if (queryMember.isPresent()) {
            Member member = queryMember.get();
            String accessToken = jwtTokenProvider.createAccessToken(member.getId());
            String refreshToken = jwtTokenProvider.createRefreshToken(member.getId());
            member.updateToken(accessToken, refreshToken);
            memberRepository.save(member);
            return AuthConverter.toOAuthResponse(accessToken, refreshToken, member);
        } else {
            Member member = memberRepository.save(AuthConverter.toMember(kakaoProfile));
            String accessToken = jwtTokenProvider.createAccessToken(member.getId());
            String refreshToken = jwtTokenProvider.createRefreshToken(member.getId());
            member.updateToken(accessToken, refreshToken);
            memberRepository.save(member);
            return AuthConverter.toOAuthResponse(accessToken, refreshToken, member);
        }
    }

    @Override
    @Transactional
    public AuthResponseDTO.TokenRefreshResponse refresh(String refreshToken) {
        jwtTokenProvider.isTokenValid(refreshToken);
        Long id = jwtTokenProvider.getId(refreshToken);
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new AuthException(ErrorStatus.USER_NOT_FOUND));
        String newAccessToken =
                jwtTokenProvider.createAccessToken(id);
        String newRefreshToken =
                jwtTokenProvider.createRefreshToken(id);
        member.updateToken(newAccessToken, newRefreshToken);
        memberRepository.save(member);
        // refreshTokenService.saveToken(newRefreshToken);
        return AuthConverter.toTokenRefreshResponse(newAccessToken, newRefreshToken);
    }


    @Override
    public MemberResponseDTO.MyPageResponse getMyPageInfo(Member member) {

        LocalDateTime now = LocalDateTime.now();

        LocalDateTime startOfMonth = now.withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime endOfMonth = now.withHour(23).withMinute(59).withSecond(59).withNano(999999999);

        int blackCount = calendarRepository.countByMemberAndColorAndDateBetween(member, Color.BLACK, startOfMonth, endOfMonth);

        int totalCount = calendarRepository.countByMemberAndDateBetween(member, startOfMonth, endOfMonth);

        float rating = totalCount == 0 ? 0 : ((float) blackCount / totalCount) * 100;

        return MemberConverter.toMyPageResponse(member, rating);
    }

}
