package com.example.Midnight.Snacker.repository;

import com.example.Midnight.Snacker.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
