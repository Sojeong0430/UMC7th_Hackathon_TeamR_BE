package com.example.Midnight.Snacker.web.dto.PostDTO;

import com.example.Midnight.Snacker.web.dto.CommentDTO.CommentResponseDTO;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

public class PostResponseDTO {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class addPostResponseDTO{
        long postId;
    }

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class getPostResponseDTO{
        private List<PostInfoDTO> posts;
    }

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class getIndiPostResponseDTO{
        private Long memberId;
        private String nickname;
        private String title;
        private String body;
        private LocalDate date;
        private String imageUrl;
        private List<CommentResponseDTO.CommentInfoDTO> comments;
        private float rating;
    }
}
