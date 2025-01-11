package com.example.Midnight.Snacker.service.PostService;

import com.example.Midnight.Snacker.apiPayload.exception.handler.PostHandler;
import com.example.Midnight.Snacker.domain.Member;
import com.example.Midnight.Snacker.domain.Post;
import com.example.Midnight.Snacker.repository.MemberRepository;
import com.example.Midnight.Snacker.repository.PostRepository;
import com.example.Midnight.Snacker.apiPayload.code.status.ErrorStatus;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    @Override
    public Post AddPost(String title, String body, String imageUrl, Member member) {
        Post newPost = Post.builder()
                .title(title)
                .body(body)
                .member(member)
                .image_url(imageUrl).build();
        System.out.println(newPost.getTitle()+newPost.getBody()+newPost.getImage_url());
        postRepository.save(newPost);
        return newPost;
    } //게시글 등록

    @Override
    public void DeletePost(long id) {
        Post post = postRepository.findById(id).orElseThrow(() ->new PostHandler(ErrorStatus.POST_NOT_FOUND));
        postRepository.delete(post);
    } // 게시글 삭제

}
