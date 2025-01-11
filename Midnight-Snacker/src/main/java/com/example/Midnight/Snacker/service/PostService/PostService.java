package com.example.Midnight.Snacker.service.PostService;
import com.example.Midnight.Snacker.domain.Member;
import com.example.Midnight.Snacker.domain.Post;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

public interface PostService {

    Post AddPost(String title, String body, String imageUrl, LocalDateTime date, Member member); //게시글 등록

    void DeletePost(long id); //게시글 삭제
}
