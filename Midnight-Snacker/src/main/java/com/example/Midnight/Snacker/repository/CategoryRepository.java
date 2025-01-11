package com.example.Midnight.Snacker.repository;

import com.example.Midnight.Snacker.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
