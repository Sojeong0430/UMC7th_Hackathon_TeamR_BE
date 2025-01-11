package com.example.Midnight.Snacker.web.controller;

import com.example.Midnight.Snacker.apiPayload.ApiResponse;
import com.example.Midnight.Snacker.apiPayload.code.status.SuccessStatus;
import com.example.Midnight.Snacker.domain.Member;
import com.example.Midnight.Snacker.domain.enums.Category;
import com.example.Midnight.Snacker.domain.enums.Color;
import com.example.Midnight.Snacker.security.handler.annotation.AuthUser;
import com.example.Midnight.Snacker.service.CalendarService.CalendarService;
import com.example.Midnight.Snacker.service.CalendarService.CalendarServiceImpl;
import com.example.Midnight.Snacker.web.dto.CalendarDTO.CalendarResponseDTO;
import com.example.Midnight.Snacker.web.dto.CalendarDTO.RegisterRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.*;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/calendar")
public class CalendarController {
    private final CalendarService calendarService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "캘린더 등록")
    public ApiResponse<Long> registerCalendar(
            @Parameter(name = "user", hidden = true) @AuthUser Member member,
            @RequestParam(value = "category")Category category,
            @RequestParam(value = "colorType")Color color,
            @RequestPart("request")RegisterRequestDTO request,
            @RequestPart(value = "image") MultipartFile image){
        Long calendarId = calendarService.addRecord(member, category,
                color, image, request);

        return ApiResponse.of(SuccessStatus.ADD_CALENDAR_OK, calendarId);
    }

    @GetMapping
    @Operation
    public ApiResponse<CalendarResponseDTO> showCalendar(
            @RequestParam(value = "date") LocalDate date){

        return ApiResponse.of(SuccessStatus.INQUERY_MONTH_CALENDAR_OK,)
    }
}
