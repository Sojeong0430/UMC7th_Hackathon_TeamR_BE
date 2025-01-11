package com.example.Midnight.Snacker.service.PostService;

import com.example.Midnight.Snacker.apiPayload.exception.handler.PostHandler;
import com.example.Midnight.Snacker.domain.Member;
import com.example.Midnight.Snacker.domain.Post;
import com.example.Midnight.Snacker.repository.MemberRepository;
import com.example.Midnight.Snacker.repository.PostRepository;
import com.example.Midnight.Snacker.apiPayload.code.status.ErrorStatus;
import com.example.Midnight.Snacker.web.controller.PostController;
import com.example.Midnight.Snacker.web.dto.PostDTO.PostInfoDTO;
import com.example.Midnight.Snacker.web.dto.PostDTO.PostResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    //private fin

    @Override
    public Post AddPost(String title, String body, String imageUrl, LocalDateTime date, Member member) {
        Post newPost = Post.builder()
                .title(title)
                .body(body)
                .member(member)
                .date(date)
                .imageUrl(imageUrl)
                .build();
        //System.out.println(newPost.getTitle()+newPost.getBody()+newPost.getImage_url());

        Post savedPost = postRepository.save(newPost);

        return savedPost;
    } //게시글 등록

    @Override
    public void DeletePost(long id) {
        Post post = postRepository.findById(id).orElseThrow(() ->new PostHandler(ErrorStatus.POST_NOT_FOUND));
        postRepository.delete(post);
    } // 게시글 삭제

    @Override
    @Transactional
    public List<PostInfoDTO> getPostInfo(){
        List<Post> posts = postRepository.findAll();

        return posts.stream()
                . map(post -> new PostInfoDTO(
                        post.getId(),
                        post.getTitle(),
                        post.getBody(),
                        post.getDate().toLocalDate(),
                        post.getImageUrl(),
                        post.
                )).toList();
    }


    @Override
    @Transactional
    public PostResponseDTO.getPostResponseDTO getPost(Member member){

    }
}
