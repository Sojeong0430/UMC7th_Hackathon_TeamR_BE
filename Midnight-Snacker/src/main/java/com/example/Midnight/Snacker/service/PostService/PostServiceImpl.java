package com.example.Midnight.Snacker.service.PostService;

import com.example.Midnight.Snacker.apiPayload.exception.handler.PostHandler;
import com.example.Midnight.Snacker.domain.Comment;
import com.example.Midnight.Snacker.domain.Member;
import com.example.Midnight.Snacker.domain.Post;
import com.example.Midnight.Snacker.domain.enums.Color;
import com.example.Midnight.Snacker.repository.CalendarRepository;
import com.example.Midnight.Snacker.repository.CommentRepository;
import com.example.Midnight.Snacker.repository.MemberRepository;
import com.example.Midnight.Snacker.repository.PostRepository;
import com.example.Midnight.Snacker.apiPayload.code.status.ErrorStatus;
import com.example.Midnight.Snacker.web.controller.PostController;
import com.example.Midnight.Snacker.web.dto.CommentDTO.CommentResponseDTO;
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
    private final CommentRepository commentRepository;
    private final CalendarRepository calendarRepository;

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
        Post post = postRepository.findById(id)
                .orElseThrow(() ->new PostHandler(ErrorStatus.POST_NOT_FOUND));
        postRepository.delete(post);
    } // 게시글 삭제

    @Override
    @Transactional
    public List<PostInfoDTO> getPostInfo(){
        List<Post> posts = postRepository.findAllByOrderByDateDesc();

        return posts.stream()
                . map(post -> new PostInfoDTO(
                        post.getId(),
                        post.getTitle(),
                        post.getBody(),
                        post.getDate().toLocalDate(),
                        post.getImageUrl(),
                        post.getComments().size()
                )).toList();
    }

    @Override
    @Transactional
    public PostResponseDTO.getPostResponseDTO getPosts(){
        List<PostInfoDTO> posts = getPostInfo();

        return new PostResponseDTO.getPostResponseDTO(posts);
    }

    @Override
    @Transactional
    public List<CommentResponseDTO.CommentInfoDTO> getComments(Long postId){
        List<Comment> comments = commentRepository.findAllByPostId(postId);

        return comments.stream()
                . map(comment -> new CommentResponseDTO.CommentInfoDTO(
                        comment.getId(),
                        comment.getMember().getId(),
                        comment.getMember().getNickname(),
                        getRate(comment.getMember()),
                        comment.getContent(),
                        comment.getDate().toLocalDate()
                )).toList();
    }

    float getRate(Member member){
        LocalDateTime now = LocalDateTime.now();

        LocalDateTime startOfMonth = now.withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime endOfMonth = now.withHour(23).withMinute(59).withSecond(59).withNano(999999999);

        int blackCount = calendarRepository.countByMemberAndColorAndDateBetween(member, Color.BLACK, startOfMonth, endOfMonth);

        int totalCount = calendarRepository.countByMemberAndDateBetween(member, startOfMonth, endOfMonth);

        float rating = totalCount == 0 ? 0 : ((float) blackCount / totalCount) * 100;
        return rating;
    }


    @Override
    @Transactional
    public PostResponseDTO.getIndiPostResponseDTO getPost(Long postId){
        List<CommentResponseDTO.CommentInfoDTO> comments = getComments(postId);

        Post post = postRepository.findById(postId).get();


        return new PostResponseDTO.getIndiPostResponseDTO(
                post.getMember().getId(),
                post.getMember().getNickname(),
                post.getTitle(),
                post.getBody(),
                post.getImageUrl(),
                comments,
                getRate(post.getMember()));
    }

}
