package com.example.Midnight.Snacker.web.dto.CommentDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class CommentRequestDTO {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class addCommentRequestDTO {
        long commentId;
    }

}
