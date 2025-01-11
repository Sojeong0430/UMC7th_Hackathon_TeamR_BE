package com.example.Midnight.Snacker.repository;

import com.example.Midnight.Snacker.domain.Calendar;
import com.example.Midnight.Snacker.domain.Member;

import com.example.Midnight.Snacker.domain.enums.Color;

import com.example.Midnight.Snacker.web.dto.CalendarDTO.CalendarInfoDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


public interface CalendarRepository extends JpaRepository<Calendar, Long> {
    List<Calendar> findAllByDateBetweenOrderByDateAsc(LocalDateTime startDate, LocalDateTime endDate);

    List<Calendar> findAllByMember(Member member);

    // 특정 멤버와 색상, 날짜 범위로 필터링하여 개수 가져오기
    int countByMemberAndColorAndDateBetween(Member member, Color color, LocalDateTime startDate, LocalDateTime endDate);

    // 특정 멤버와 날짜 범위로 필터링하여 전체 개수 가져오기
    int countByMemberAndDateBetween(Member member, LocalDateTime startDate, LocalDateTime endDate);
}
