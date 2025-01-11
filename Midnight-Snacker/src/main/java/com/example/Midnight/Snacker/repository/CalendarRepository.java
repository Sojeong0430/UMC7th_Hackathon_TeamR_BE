package com.example.Midnight.Snacker.repository;

import com.example.Midnight.Snacker.domain.Calendar;
import com.example.Midnight.Snacker.web.dto.CalendarDTO.CalendarInfoDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


public interface CalendarRepository extends JpaRepository<Calendar, Long> {
    List<Calendar> findAllByDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}
