package com.example.Midnight.Snacker.web.controller;

import com.example.Midnight.Snacker.apiPayload.ApiResponse;
import com.example.Midnight.Snacker.apiPayload.code.status.SuccessStatus;
import com.example.Midnight.Snacker.converter.CalenderConverter;
import com.example.Midnight.Snacker.domain.Calendar;
import com.example.Midnight.Snacker.domain.Member;
import com.example.Midnight.Snacker.domain.enums.CategoryE;
import com.example.Midnight.Snacker.domain.enums.Color;
import com.example.Midnight.Snacker.security.handler.annotation.AuthUser;
import com.example.Midnight.Snacker.service.CalendarService.CalendarService;
import com.example.Midnight.Snacker.service.calenderCountService.CalenderCountService;
import com.example.Midnight.Snacker.web.dto.CalendarDTO.CalendarResponseDTO;
import com.example.Midnight.Snacker.web.dto.calenderCountDTO.calenderCountResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/calendar")
public class CalendarController {
    private final CalendarService calendarService;
    private final CalenderCountService calenderCountService;
    private final CalenderConverter calenderConverter;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "캘린더 등록")
    public ApiResponse<Long> registerCalendar(
            @Parameter(name = "user", hidden = true) @AuthUser Member member,
            @RequestParam(value = "categoryE") CategoryE categoryE,
            @RequestParam(value = "colorType")Color color,
            @RequestParam(value = "date")LocalDateTime date,
            @RequestParam(value = "detailFood") String detailFood,
            @RequestPart(value = "image") MultipartFile image){
        //Long calendarId = calendarService.addRecord(member, category, color, image, request);

        Long calendarId = calendarService.addRecord(member, categoryE,
                color, image, date, detailFood);

        return ApiResponse.of(SuccessStatus.ADD_CALENDAR_OK, calendarId);
    }

    @GetMapping("/monthly")
    @Operation(
            summary = "월별 기록 조회 API")
    public ApiResponse<CalendarResponseDTO.CalendarResponseMonthlyListDTO> getMonthlyCalendar(
            @Parameter(name = "user", hidden = true) @AuthUser Member member,
            @RequestParam(value = "year") int year,
            @RequestParam(value = "month") int month){

        CalendarResponseDTO.CalendarResponseMonthlyListDTO response = calendarService.getMonthlyRecords(year, month, member);

        return ApiResponse.of(SuccessStatus.INQUERY_MONTH_CALENDAR_OK,response);
    }

    @GetMapping("/daily")
    @Operation(
            summary = "일별 기록 조회 API")
    public ApiResponse<List<CalendarResponseDTO.CalendarResponseDailyDTO>> getDailyCalendar(
            @Parameter(name = "user", hidden = true) @AuthUser Member member,
            @RequestParam(value = "date") LocalDate date){

        List<CalendarResponseDTO.CalendarResponseDailyDTO> response = calendarService.getDailyRecord(date, member);

        return ApiResponse.of(SuccessStatus.INQUERY_DATE_CALENDAR_OK,response);
    }

    @DeleteMapping("/{calendarId}")
    @Operation(summary = "기록 삭제")
    public ApiResponse<Void> deleteCalendar(
            @Parameter(name = "user", hidden = true) @AuthUser Member member,
            @PathVariable("calendarId") Long calendarId){
        calendarService.deleteRecord(member, calendarId);
        return ApiResponse.of(SuccessStatus.DELETE_RECORD_OK,null);
    }


    @GetMapping("/count")
    @Operation(summary = "한달 동안의 야식 카테고리 결산")
    public ApiResponse<calenderCountResponseDTO.MissionPreviewListDTO> monthCategoryCount(
            @Parameter(name = "user", hidden = true) @AuthUser Member member)
    {
        List<Calendar> ll = calenderCountService.getCalenderList(member);
        List<calenderCountResponseDTO.monthCountPreviewDTO> kk = calenderCountService.getMonthCountPreviewDTOList();
        calenderCountService.categoryCount(kk,ll);

        calenderCountResponseDTO.MissionPreviewListDTO result = calenderConverter.toCCList(kk);

        return ApiResponse.of(SuccessStatus.MONTH_CATEGORY_COUNT_OK,result);
    }

}
