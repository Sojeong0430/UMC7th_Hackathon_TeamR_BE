package com.example.Midnight.Snacker.service.CommentService;

import com.example.Midnight.Snacker.domain.Comment;

public interface CommentService {

    Comment addComment(long postId);
    Comment deleteComment(long commentId);
}
