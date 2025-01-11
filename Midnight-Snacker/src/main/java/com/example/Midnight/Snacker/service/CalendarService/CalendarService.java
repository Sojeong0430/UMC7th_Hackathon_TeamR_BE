package com.example.Midnight.Snacker.service.CalendarService;

import com.example.Midnight.Snacker.repository.CalendarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CalendarService {
    private final CalendarRepository calendarRepository;

    //야식 기록 추가 method
    public Long addRecord(LocalDate date, String)
    //월별 기록 조회 method

    //일별 기록 조회 method

    //기록 삭제 method

}
