package com.example.Midnight.Snacker.web.dto.CalendarDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CalendarResponseDTO {
    private int blackCount;
    private int whiteCount;
    private List<CalendarInfoDTO> calendarInfoDTOS;
}
