package com.example.Midnight.Snacker.service.PostService;

import com.example.Midnight.Snacker.apiPayload.exception.handler.PostHandler;
import com.example.Midnight.Snacker.domain.Member;
import com.example.Midnight.Snacker.domain.Post;
import com.example.Midnight.Snacker.repository.MemberRepository;
import com.example.Midnight.Snacker.repository.PostRepository;
import com.example.Midnight.Snacker.apiPayload.code.status.ErrorStatus;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    @Override
    public Post AddPost(String title, String body, String imageUrl, Member member) {
        Member postAddmember = member;
        return Post.builder()
                .title(title)
                .body(body)
                .member(postAddmember)
                .image_url(imageUrl).build();
    } //게시글 등록

    @Override
    public void DeletePost(long id) {
        Post post = postRepository.findById(id).orElseThrow(() ->new PostHandler(ErrorStatus.POST_NOT_FOUND));
        postRepository.delete(post);
    } // 게시글 삭제

}
