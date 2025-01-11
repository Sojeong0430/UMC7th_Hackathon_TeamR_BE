package com.example.Midnight.Snacker.service.PostService;
import com.example.Midnight.Snacker.domain.Member;
import com.example.Midnight.Snacker.domain.Post;
import org.springframework.stereotype.Service;

public interface PostService {

    public Post AddPost(String title, String body, String imageUrl, Member member); //게시글 등록

    public void DeletePost(long id); //게시글 삭제
}
