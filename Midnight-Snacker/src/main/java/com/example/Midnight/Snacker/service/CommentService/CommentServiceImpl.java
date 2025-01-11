package com.example.Midnight.Snacker.service.CommentService;

import com.example.Midnight.Snacker.apiPayload.code.status.ErrorStatus;
import com.example.Midnight.Snacker.apiPayload.exception.handler.CommentHandler;
import com.example.Midnight.Snacker.apiPayload.exception.handler.PostHandler;
import com.example.Midnight.Snacker.domain.Comment;
import com.example.Midnight.Snacker.domain.Member;
import com.example.Midnight.Snacker.domain.Post;
import com.example.Midnight.Snacker.repository.CommentRepository;
import com.example.Midnight.Snacker.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Override
    public Comment addComment(Member member, long postId, String content) {
        Post post = postRepository.findById(postId).orElseThrow(() ->new PostHandler(ErrorStatus.POST_NOT_FOUND));
        Comment newComment = Comment.builder()
                .content(content)
                .post(post)
                .member(member)
                .build();
        commentRepository.save(newComment);
        return newComment;
    } //댓글 달기

    @Override
    @Transactional
    public void deleteComment(long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(() ->new CommentHandler(ErrorStatus.COMMENT_NOT_FOUND));
        commentRepository.delete(comment);
    }//댓글 삭제
}
