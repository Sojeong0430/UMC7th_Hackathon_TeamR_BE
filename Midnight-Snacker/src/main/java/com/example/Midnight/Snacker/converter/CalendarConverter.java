package com.example.Midnight.Snacker.converter;

import com.example.Midnight.Snacker.domain.Calendar;
import com.example.Midnight.Snacker.domain.Member;
import com.example.Midnight.Snacker.domain.enums.Category;
import com.example.Midnight.Snacker.domain.enums.Color;
import com.example.Midnight.Snacker.web.dto.CalendarDTO.RegisterRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@RequiredArgsConstructor
public class CalendarConverter {
    public Calendar toCalendar(Member member,
                               RegisterRequestDTO request,
                               String imageUrl,
                               Category category,
                               Color color) {
        return Calendar.builder()
                .date(request.getDate())
                .detailFood(request.getDetailFood())
                .color(color)
                .category(category)
                .imageUrl(imageUrl)
                .member(member)
                .build();
    }
}
