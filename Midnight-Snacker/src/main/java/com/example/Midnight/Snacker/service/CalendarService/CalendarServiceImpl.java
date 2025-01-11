package com.example.Midnight.Snacker.service.CalendarService;

import com.example.Midnight.Snacker.apiPayload.exception.CustomException;
import com.example.Midnight.Snacker.converter.CalendarConverter;
import com.example.Midnight.Snacker.domain.Calendar;
import com.example.Midnight.Snacker.domain.Member;
import com.example.Midnight.Snacker.domain.enums.Category;
import com.example.Midnight.Snacker.domain.enums.Color;
import com.example.Midnight.Snacker.repository.CalendarRepository;
import com.example.Midnight.Snacker.repository.MemberRepository;
import com.example.Midnight.Snacker.service.S3Service.S3ImageService;
import com.example.Midnight.Snacker.web.dto.CalendarDTO.CalendarInfoDTO;
import com.example.Midnight.Snacker.web.dto.CalendarDTO.CalendarResponseDTO;
import com.example.Midnight.Snacker.web.dto.CalendarDTO.RegisterRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CalendarServiceImpl implements CalendarService {
    private final CalendarRepository calendarRepository;
    private final S3ImageService s3ImageService;
    private final CalendarConverter calendarConverter;
    private final MemberRepository memberRepository;

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

        //member count 반영하기
        if("BLACK".equalsIgnoreCase(color.name())){
            member.setBlackCount(member.getBlackCount()+1);
        }
        if("White".equalsIgnoreCase(color.name())){
            member.setWhiteCount(member.getWhiteCount()+1);
        }

        memberRepository.save(member);

       return savedCalendar.getId();
    }
    public List<CalendarInfoDTO> getCalendarInfo(LocalDate localDate){
        //LocalDate를 LocalDateTime의 범위로 변환
        LocalDateTime startDateTime = localDate.atStartOfDay();
        LocalDateTime endDateTime = localDate.atTime(23, 59, 59);

        //repository로 찾기
        List<Calendar> calendars = calendarRepository.findAllByDateBetween(startDateTime, endDateTime);

        return calendars.stream()
                .map(calendar -> new CalendarInfoDTO(
                        calendar.getId(),
                        calendar.getCategory(),
                        calendar.getColor(),
                        calendar.getDetailFood(),
                        calendar.getImageUrl()
                )).toList();
    }
    //월일별 기록 조회 method
    public CalendarResponseDTO getRecord(LocalDate localDate){
        List<CalendarInfoDTO> calendarInfos = getCalendarInfo(localDate);

        int blackCount

        List<CalendarInfoDTO> calendarInfos =
    }

    //기록 삭제 method

}
