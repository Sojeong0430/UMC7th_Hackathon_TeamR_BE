package com.example.Midnight.Snacker.web.dto.CommentDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.convert.Jsr310Converters;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CommentResponseDTO {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CommentPostResponseDTO{
        long commentId;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CommentInfoDTO{
        private Long commentId;
        private Long memberId;
        private String nickname;
        private Float percent;
        private String content;
        private LocalDateTime date;
    }
}
