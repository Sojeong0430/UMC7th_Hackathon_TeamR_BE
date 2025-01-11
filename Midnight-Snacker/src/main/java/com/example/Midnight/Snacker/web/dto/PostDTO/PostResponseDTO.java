package com.example.Midnight.Snacker.web.dto.PostDTO;

import lombok.*;

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
}
