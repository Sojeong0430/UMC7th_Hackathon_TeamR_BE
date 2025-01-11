package com.example.Midnight.Snacker.service.CalendarService;

import com.example.Midnight.Snacker.domain.Member;
import com.example.Midnight.Snacker.domain.enums.Category;
import com.example.Midnight.Snacker.domain.enums.Color;
import com.example.Midnight.Snacker.web.dto.CalendarDTO.RegisterRequestDTO;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface CalendarService {
    Long addRecord(Member member, Category category, Color color, MultipartFile image, RegisterRequestDTO request);
}
