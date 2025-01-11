package com.example.Midnight.Snacker.web.controller;

import com.example.Midnight.Snacker.apiPayload.ApiResponse;
import com.example.Midnight.Snacker.apiPayload.code.status.SuccessStatus;
import com.example.Midnight.Snacker.converter.CommentConverter;
import com.example.Midnight.Snacker.converter.PostConverter;
import com.example.Midnight.Snacker.domain.Comment;
import com.example.Midnight.Snacker.domain.Member;
import com.example.Midnight.Snacker.security.handler.annotation.AuthUser;
import com.example.Midnight.Snacker.service.CommentService.CommentService;
import com.example.Midnight.Snacker.web.dto.CommentDTO.CommentRequestDTO;
import com.example.Midnight.Snacker.web.dto.CommentDTO.CommentResponseDTO;
import com.example.Midnight.Snacker.web.dto.PostDTO.PostResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @Operation(summary = "댓글 등록 API", description = "댓글을 등록합니다")
    @PostMapping("/api/post/{postId}/comment")
    public ApiResponse<CommentResponseDTO. CommentPostResponseDTO> postComment(@Parameter(name = "user", hidden = true) @AuthUser Member member,
                                                                               @PathVariable("postId") Integer postId,
                                                                               @RequestBody CommentRequestDTO.addCommentRequestDTO request) {
        String content = request.getContent();
        Comment newcomment = commentService.addComment(member,postId,content);
        return ApiResponse.of(SuccessStatus.COMMENT_POST_OK, CommentConverter.addCommentToResultDTO(newcomment));
    }

    @Operation(summary = "댓글 삭제 API", description = "댓글을 삭제합니다")
    @DeleteMapping("/api/post/comment/{commentId}")
    public ApiResponse<Void> deleteComment(@PathVariable("commentId") long commentId) {
        commentService.deleteComment(commentId);
        return ApiResponse.of(SuccessStatus.COMMENT_DELETE_OK, null);
    }
}
