package com.example.Midnight.Snacker.converter;

import com.example.Midnight.Snacker.domain.Member;
import com.example.Midnight.Snacker.web.dto.MemberDTO.MemberResponseDTO;

public class MemberConverter {
    public static MemberResponseDTO.MyPageResponse toMyPageResponse(Member member) {
        return MemberResponseDTO.MyPageResponse.builder()
                .memberId(member.getId())
                .nickname(member.getNickname())
                .build();
    }
}
