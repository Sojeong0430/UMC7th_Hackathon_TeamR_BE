package com.example.Midnight.Snacker.converter;

import com.example.Midnight.Snacker.domain.Member;
import com.example.Midnight.Snacker.web.dto.MemberDTO.MemberResponseDTO;

public class MemberConverter {
    public static MemberResponseDTO.MyPageResponse toMyPageResponse(Member member, float rating) {
        return MemberResponseDTO.MyPageResponse.builder()
                .memberId(member.getId())
                .nickname(member.getNickname())
                .email(member.getEmail())
                .categories(rating)
                .build();
    }
}
