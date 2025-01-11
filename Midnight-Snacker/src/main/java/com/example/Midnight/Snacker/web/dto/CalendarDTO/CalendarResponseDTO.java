package com.example.Midnight.Snacker.web.dto.CalendarDTO;

import com.example.Midnight.Snacker.domain.enums.CategoryE;
import com.example.Midnight.Snacker.domain.enums.Color;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;


public class CalendarResponseDTO {

    private List<CalendarResponseDailyDTO> dailyRecords;
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CalendarResponseDailyDTO {
        private Color color;
        private String ImgUrl;
        private String foodName;
        private CategoryE category;
        private Long foodId;

    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CalendarResponseMonthlyListDTO {
        private int blackCount;
        private int whiteCount;
        private List<CalendarResponseMonthlyDTO> calendarInfoDTOS;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CalendarResponseMonthlyDTO {
        private LocalDate date;
        private Color color;

    }
}
