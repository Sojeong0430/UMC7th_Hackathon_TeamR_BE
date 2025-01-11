package com.example.Midnight.Snacker.service.CalendarService;

import com.example.Midnight.Snacker.domain.Member;
import com.example.Midnight.Snacker.domain.enums.CategoryE;
import com.example.Midnight.Snacker.domain.enums.Color;
import com.example.Midnight.Snacker.web.dto.CalendarDTO.CalendarInfoDTO;
import com.example.Midnight.Snacker.web.dto.CalendarDTO.CalendarResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface CalendarService {

    //Long addRecord(Member member, Category category, Color color, MultipartFile image, RegisterRequestDTO request);
    Long addRecord(Member member, CategoryE categoryE, Color color, MultipartFile image, LocalDateTime date, String detailFood);

    List<CalendarInfoDTO> getCalendarInfo(LocalDate localDate);
    CalendarResponseDTO getRecord(LocalDate date, Member member);
    void deleteRecord(Member member, Long calendarId);
}
