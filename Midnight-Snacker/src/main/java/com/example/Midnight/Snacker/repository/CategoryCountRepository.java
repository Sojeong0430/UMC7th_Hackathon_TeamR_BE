package com.example.Midnight.Snacker.repository;

import com.example.Midnight.Snacker.domain.CategoryCount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Calendar;

public interface CategoryCountRepository extends JpaRepository<CategoryCount, Long> {

}
