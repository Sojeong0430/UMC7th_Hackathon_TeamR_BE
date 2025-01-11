package com.example.Midnight.Snacker.repository;

import com.example.Midnight.Snacker.domain.Category;
import com.example.Midnight.Snacker.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);

}
