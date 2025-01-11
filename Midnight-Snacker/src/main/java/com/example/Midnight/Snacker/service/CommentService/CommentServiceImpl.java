package com.example.Midnight.Snacker.service.CommentService;

import com.example.Midnight.Snacker.domain.Comment;
import com.example.Midnight.Snacker.domain.Post;
import com.example.Midnight.Snacker.repository.CommentRepository;
import com.example.Midnight.Snacker.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Override
    public Comment addComment(long postId) {
        Post post = postRepository.findById(postId).get();
        Comment comment;
        return null;
    } //댓글 달기

    @Override
    public Comment deleteComment(long commentId) {
        return null;
    }//댓글 삭제
}
