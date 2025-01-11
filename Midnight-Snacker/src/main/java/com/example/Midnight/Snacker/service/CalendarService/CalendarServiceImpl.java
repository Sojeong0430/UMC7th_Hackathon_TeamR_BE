package com.example.Midnight.Snacker.service.CalendarService;

import com.example.Midnight.Snacker.apiPayload.code.status.ErrorStatus;
import com.example.Midnight.Snacker.apiPayload.exception.CalendarException;
import com.example.Midnight.Snacker.apiPayload.exception.CustomException;
import com.example.Midnight.Snacker.converter.CalenderConverter;
import com.example.Midnight.Snacker.domain.Calendar;
import com.example.Midnight.Snacker.domain.Member;
import com.example.Midnight.Snacker.domain.Post;
import com.example.Midnight.Snacker.domain.enums.CategoryE;
import com.example.Midnight.Snacker.domain.enums.Color;
import com.example.Midnight.Snacker.repository.CalendarRepository;
import com.example.Midnight.Snacker.repository.MemberRepository;
import com.example.Midnight.Snacker.service.S3Service.S3ImageService;
import com.example.Midnight.Snacker.web.dto.CalendarDTO.CalendarInfoDTO;
import com.example.Midnight.Snacker.web.dto.CalendarDTO.CalendarResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CalendarServiceImpl implements CalendarService {
    private final CalendarRepository calendarRepository;
    private final S3ImageService s3ImageService;
    private final CalenderConverter calenderConverter;
    private final MemberRepository memberRepository;

    //야식 기록 추가 method
    @Override
    @Transactional
    public Long addRecord(Member member, CategoryE categoryE, Color color, MultipartFile image, LocalDateTime date, String detailFood){
        //upload file 하기
        String imageUrl = s3ImageService.upload(image);
       //Calendar 엔티티 생성

        //Calendar calendar = calendarConverter.toCalendar(member,request, imageUrl, category, color);
        Calendar calendar = calenderConverter.toCalendar(member, date, detailFood, imageUrl, categoryE, color);

        Calendar savedCalendar = calendarRepository.save(calendar);

        //member count 반영하기
        if("BLACK".equalsIgnoreCase(color.name())){
            member.setBlackCount(member.getBlackCount()+1);
        }
        if("White".equalsIgnoreCase(color.name())){
            member.setWhiteCount(member.getWhiteCount()+1);
        }

        member.setTotalCount();

        memberRepository.save(member);

       return savedCalendar.getId();
    }

    @Override
    public CalendarResponseDTO.CalendarResponseMonthlyListDTO getMonthlyRecords(int year, int month, Member member) {
        LocalDate startOfMonth = YearMonth.of(year, month).atDay(1);
        LocalDate endOfMonth = YearMonth.of(year, month).atEndOfMonth();

        List<Calendar> calendars = calendarRepository.findByDateBetweenAndMember(startOfMonth.atStartOfDay(), endOfMonth.atStartOfDay(), member);

        int blackCount = (int) calendars.stream().filter(calendar -> calendar.getColor() == Color.BLACK).count();
        int whiteCount = (int) calendars.stream().filter(calendar -> calendar.getColor() == Color.WHITE).count();

        List<CalendarResponseDTO.CalendarResponseMonthlyDTO> calendarInfoDTOS = calendars.stream()
                .map(calendar -> new CalendarResponseDTO.CalendarResponseMonthlyDTO(calendar.getDate().toLocalDate(), calendar.getColor()))
                .collect(Collectors.toList());

        return new CalendarResponseDTO.CalendarResponseMonthlyListDTO(blackCount, whiteCount, calendarInfoDTOS);
    }

    @Override
    public List<CalendarResponseDTO.CalendarResponseDailyDTO> getDailyRecord(LocalDate date, Member member) {
        LocalDateTime startDateTime = date.atTime(0,0,0);
        LocalDateTime endDateTime = date.atTime(23, 59, 59);
        List<CalendarResponseDTO.CalendarResponseDailyDTO> calendars = calendarRepository.findAllByMemberAndDateBetweenOrderByDateAsc(member, startDateTime, endDateTime)
                .stream().map(calendar -> new CalendarResponseDTO.CalendarResponseDailyDTO(
                        calendar.getColor(),
                        calendar.getImageUrl(),
                        calendar.getDetailFood(),
                        calendar.getCategoryE(),
                        calendar.getId()
                ))
                .collect(Collectors.toList());

        return calendars;
    }

    //기록 삭제 method
    @Override
    @Transactional
    public void deleteRecord(Member member, Long calendarId){
        Calendar calendar =
                calendarRepository.findById(calendarId)
                        .orElseThrow(() -> new CustomException(ErrorStatus.CALENDAR_NOT_FOUND));

        calendarRepository.deleteById(calendarId);


        //member count 반영하기
        if("BLACK".equalsIgnoreCase(calendar.getColor().name())){
            member.setBlackCount(member.getBlackCount()-1);
        }
        if("White".equalsIgnoreCase(calendar.getColor().name())){
            member.setWhiteCount(member.getWhiteCount()-1);
        }

        member.setTotalCount();

        memberRepository.save(member);

    }
}
