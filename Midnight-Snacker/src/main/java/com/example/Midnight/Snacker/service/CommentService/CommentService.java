package com.example.Midnight.Snacker.service.CommentService;

import com.example.Midnight.Snacker.domain.Comment;
import com.example.Midnight.Snacker.domain.Member;

public interface CommentService {
    Comment addComment(Member member,long postId, String content);
    Comment deleteComment(long commentId);
}
