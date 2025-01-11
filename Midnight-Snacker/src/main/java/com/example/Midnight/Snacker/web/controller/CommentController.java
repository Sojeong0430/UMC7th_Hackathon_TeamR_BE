package com.example.Midnight.Snacker.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentController {

    @PostMapping("/api/post/{postId}/comment")
    public String postComment(@PathVariable("postId") Integer postId) {

        return postComment(postId);
    }

    @DeleteMapping("/api/post/{commentId}")
    public String deleteComment(@PathVariable("commentId") Integer commentId) {
        return deleteComment(commentId);
    }

}
