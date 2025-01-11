package com.example.Midnight.Snacker.converter;

import com.example.Midnight.Snacker.domain.Calendar;
import com.example.Midnight.Snacker.domain.Member;
import com.example.Midnight.Snacker.domain.enums.CategoryE;
import com.example.Midnight.Snacker.domain.enums.Color;
import com.example.Midnight.Snacker.web.dto.calenderCountDTO.calenderCountResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CalenderConverter {
    public Calendar toCalendar(Member member,
                               LocalDateTime date,
                               String detailFood,
                               String imageUrl,
                               CategoryE categoryE,
                               Color color) {
        return Calendar.builder()
                .date(date)
                .detailFood(detailFood)
                .color(color)
                .categoryE(categoryE)
                .imageUrl(imageUrl)
                .member(member)
                .build();
    }

    public calenderCountResponseDTO.MissionPreviewListDTO toCCList (List<calenderCountResponseDTO.monthCountPreviewDTO> countPreviewDTOList){
        return calenderCountResponseDTO.MissionPreviewListDTO.builder()
                .monthCountPreviewDTOList(countPreviewDTOList)
                .build();
    }
}
