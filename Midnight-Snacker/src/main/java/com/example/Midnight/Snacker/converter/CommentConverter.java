package com.example.Midnight.Snacker.converter;

import com.example.Midnight.Snacker.domain.Comment;
import com.example.Midnight.Snacker.domain.Post;
import com.example.Midnight.Snacker.web.dto.CommentDTO.CommentResponseDTO;
import com.example.Midnight.Snacker.web.dto.PostDTO.PostResponseDTO;

public class CommentConverter {

    public static CommentResponseDTO.CommentPostResponseDTO addCommentToResultDTO(Comment comment){
        return CommentResponseDTO.CommentPostResponseDTO.builder()
                .commentId(comment.getId())
                .build();
    }

}
