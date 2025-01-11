package com.example.Midnight.Snacker.web.controller;

import com.example.Midnight.Snacker.apiPayload.ApiResponse;
import com.example.Midnight.Snacker.converter.CommentConverter;
import com.example.Midnight.Snacker.converter.PostConverter;
import com.example.Midnight.Snacker.domain.Comment;
import com.example.Midnight.Snacker.domain.Member;
import com.example.Midnight.Snacker.security.handler.annotation.AuthUser;
import com.example.Midnight.Snacker.service.CommentService.CommentService;
import com.example.Midnight.Snacker.web.dto.CommentDTO.CommentRequestDTO;
import com.example.Midnight.Snacker.web.dto.CommentDTO.CommentResponseDTO;
import com.example.Midnight.Snacker.web.dto.PostDTO.PostResponseDTO;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/api/post/{postId}/comment")
    public ApiResponse<CommentResponseDTO. CommentPostResponseDTO> postComment(@Parameter(name = "user", hidden = true) @AuthUser Member member,
                                                                               @PathVariable("postId") Integer postId,
                                                                               @RequestBody CommentRequestDTO.addCommentRequestDTO request) {
        String content = request.getContent();
        Comment newcomment = commentService.addComment(member,postId,content);
        return ApiResponse.onSuccess(CommentConverter.addCommentToResultDTO(newcomment));
    } //

    /*@DeleteMapping("/api/post/{commentId}")
    public String deleteComment(@PathVariable("commentId") Integer commentId) {
        return deleteComment(commentId);
    }*/

}
