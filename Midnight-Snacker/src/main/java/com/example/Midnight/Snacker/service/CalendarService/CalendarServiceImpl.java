package com.example.Midnight.Snacker.service.CalendarService;

import com.example.Midnight.Snacker.apiPayload.exception.CustomException;
import com.example.Midnight.Snacker.converter.CalendarConverter;
import com.example.Midnight.Snacker.domain.Calendar;
import com.example.Midnight.Snacker.domain.Member;
import com.example.Midnight.Snacker.domain.enums.Category;
import com.example.Midnight.Snacker.domain.enums.Color;
import com.example.Midnight.Snacker.repository.CalendarRepository;
import com.example.Midnight.Snacker.service.S3Service.S3ImageService;
import com.example.Midnight.Snacker.web.dto.CalendarDTO.RegisterRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CalendarServiceImpl implements CalendarService {
    private final CalendarRepository calendarRepository;
    private final S3ImageService s3ImageService;
    private final CalendarConverter calendarConverter;

    //야식 기록 추가 method
    @Override
    @Transactional
    public Long addRecord(Member member, Category category, Color color, MultipartFile image, RegisterRequestDTO request){
        //upload file 하기
        String imageUrl = s3ImageService.upload(image);
       //Calendar 엔티티 생성

        Calendar calendar =
                calendarConverter.toCalendar(member,request, imageUrl, category, color);

        Calendar savedCalendar = calendarRepository.save(calendar);

       return savedCalendar.getId();
    }
    //월별 기록 조회 method

    //일별 기록 조회 method

    //기록 삭제 method

}
