package com.example.Midnight.Snacker.converter;

import com.example.Midnight.Snacker.domain.Member;
import com.example.Midnight.Snacker.domain.Post;
import com.example.Midnight.Snacker.web.dto.PostDTO.PostResponseDTO;

public class PostConverter {

    public static PostResponseDTO.addPostResponseDTO addPostToResultDTO(Post post){
        return PostResponseDTO.addPostResponseDTO.builder()
                .postId(post.getId())
                .build();
    }

}
