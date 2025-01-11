package com.example.Midnight.Snacker.service.PostService;
import com.example.Midnight.Snacker.domain.Member;
import com.example.Midnight.Snacker.domain.Post;
import com.example.Midnight.Snacker.web.dto.CommentDTO.CommentResponseDTO;
import com.example.Midnight.Snacker.web.dto.PostDTO.PostInfoDTO;
import com.example.Midnight.Snacker.web.dto.PostDTO.PostRequestDTO;
import com.example.Midnight.Snacker.web.dto.PostDTO.PostResponseDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

public interface PostService {

    Post AddPost(String title, String body, String imageUrl, LocalDateTime date, Member member); //게시글 등록
    List<PostInfoDTO> getPostInfo();
    PostResponseDTO.getPostResponseDTO getPosts();
    List<CommentResponseDTO.CommentInfoDTO> getComments(Long postId);
    PostResponseDTO.getIndiPostResponseDTO getPost(Long postId);
    void DeletePost(long id); //게시글 삭제
}
