package com.example.Midnight.Snacker.web.controller;

import com.example.Midnight.Snacker.apiPayload.ApiResponse;
import com.example.Midnight.Snacker.apiPayload.code.status.SuccessStatus;
import com.example.Midnight.Snacker.converter.PostConverter;
import com.example.Midnight.Snacker.domain.Member;
import com.example.Midnight.Snacker.domain.Post;
import com.example.Midnight.Snacker.repository.PostRepository;
import com.example.Midnight.Snacker.security.handler.annotation.AuthUser;
import com.example.Midnight.Snacker.service.PostService.PostService;
import com.example.Midnight.Snacker.service.S3Service.S3ImageService;
import com.example.Midnight.Snacker.web.dto.PostDTO.PostRequestDTO;
import com.example.Midnight.Snacker.web.dto.PostDTO.PostResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final S3ImageService s3ImageService;

    @ResponseStatus(code = HttpStatus.CREATED)
    @Operation(summary = "게시글 생성", description = "게시글 생성 API입니다")
    @ApiResponses({@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON201", description="등록성공")})
    @PostMapping(value = "/api/post/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ApiResponse<PostResponseDTO.addPostResponseDTO> AddPost(@Parameter(name = "user", hidden = true) @AuthUser Member member,
                                                            @RequestPart(value = "request") PostRequestDTO.addPostRequestDTO request,
                                                            @RequestPart(value = "image", required = false) MultipartFile image){
        String imageUrl = (image != null) ? s3ImageService.upload(image) : null;

        String title = request.getTitle();
        String body = request.getBody();
        LocalDateTime date = LocalDateTime.now();

        Post post = postService.AddPost(title, body, imageUrl,date ,member);
        return ApiResponse.onSuccess(PostConverter.addPostToResultDTO(post));
    }

    @DeleteMapping("/api/post/{postId}")
    public ApiResponse<Void> DeletePost(@PathVariable(name = "postId") long postId){
        postService.DeletePost(postId);
        return ApiResponse.of(SuccessStatus.POST_DELETE_OK, null);
    }

    /*@GetMapping("/api/post/all")
    @Operation
    public ApiResponse<PostResponseDTO> getAllPosts(){}
*/
}
