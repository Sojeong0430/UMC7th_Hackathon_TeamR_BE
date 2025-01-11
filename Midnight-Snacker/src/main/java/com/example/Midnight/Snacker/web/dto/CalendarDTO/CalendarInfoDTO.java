package com.example.Midnight.Snacker.web.dto.CalendarDTO;

import com.example.Midnight.Snacker.domain.enums.Category;
import com.example.Midnight.Snacker.domain.enums.Color;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter
public class CalendarInfoDTO {
    private final Long calendarId;
    private final Category category;
    private final Color color;
    private final String detailFood;
    private final String imageUrl;
}
