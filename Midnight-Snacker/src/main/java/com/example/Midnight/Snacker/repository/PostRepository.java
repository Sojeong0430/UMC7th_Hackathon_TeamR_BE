package com.example.Midnight.Snacker.repository;

import com.example.Midnight.Snacker.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
