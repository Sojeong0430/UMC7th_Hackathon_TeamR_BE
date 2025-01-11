package com.example.Midnight.Snacker.web.controller;

import com.example.Midnight.Snacker.apiPayload.ApiResponse;
import com.example.Midnight.Snacker.apiPayload.code.status.SuccessStatus;
import com.example.Midnight.Snacker.converter.PostConverter;
import com.example.Midnight.Snacker.domain.Member;
import com.example.Midnight.Snacker.domain.Post;
import com.example.Midnight.Snacker.repository.PostRepository;
import com.example.Midnight.Snacker.security.handler.annotation.AuthUser;
import com.example.Midnight.Snacker.service.PostService.PostService;
import com.example.Midnight.Snacker.web.dto.PostDTO.PostRequestDTO;
import com.example.Midnight.Snacker.web.dto.PostDTO.PostResponseDTO;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final PostRepository postRepository;

    @GetMapping("/api/post/")
    ApiResponse<PostResponseDTO.addPostResponseDTO> AddPost(@RequestBody PostRequestDTO.addPostRequestDTO request, @Parameter(name = "user", hidden = true) @AuthUser Member member){
        String title = request.getTitle();
        String body = request.getBody();
        String imageUrl = request.getImageUrl();

        Member postMember = member;

        Post post = postService.AddPost(title, body, imageUrl,member);
        return ApiResponse.onSuccess(PostConverter.addPostToResultDTO(post));
    }

    @DeleteMapping("/api/post/{postId}")
    public ApiResponse<Void> DeletePost(@PathVariable long postId){
        postService.DeletePost(postId);
        return ApiResponse.of(SuccessStatus.POST_DELETE_OK, null);
    }

}
