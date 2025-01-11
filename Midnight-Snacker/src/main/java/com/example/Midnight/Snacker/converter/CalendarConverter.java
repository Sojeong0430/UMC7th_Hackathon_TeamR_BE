package com.example.Midnight.Snacker.converter;

import com.example.Midnight.Snacker.domain.Calendar;
import com.example.Midnight.Snacker.domain.Member;
import com.example.Midnight.Snacker.domain.enums.Category;
import com.example.Midnight.Snacker.domain.enums.Color;
import com.example.Midnight.Snacker.web.dto.CalendarDTO.RegisterRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class CalendarConverter {
    public Calendar toCalendar(Member member,
                               LocalDateTime date,
                               String detailFood,
                               String imageUrl,
                               Category category,
                               Color color) {
        return Calendar.builder()
                .date(date)
                .detailFood(detailFood)
                .color(color)
                .category(category)
                .imageUrl(imageUrl)
                .member(member)
                .build();
    }
}
