package com.example.Midnight.Snacker.web.controller;

import com.example.Midnight.Snacker.apiPayload.ApiResponse;
import com.example.Midnight.Snacker.apiPayload.code.status.SuccessStatus;
import com.example.Midnight.Snacker.domain.enums.Category;
import com.example.Midnight.Snacker.domain.enums.Color;
import com.example.Midnight.Snacker.service.CalendarService.CalendarService;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/calendar")
public class CalendarController {
    private final CalendarService calendarService;

    @PostMapping
    public ApiResponse<Long> registerCalendar(
            @RequestParam(value = "category")Category category,
            @RequestParam(value = "colorType")Color color,
            @PathVariable("date")LocalDate date,
            @PathVariable("detailFood") String detailFood){
        Long calendarId = calendarService.addRecord(category,
                color, date, detailFood);

        return new ApiResponse.of(SuccessStatus._OK, "달력에 기록이 되었습니다.",calendarId);
    }
}
