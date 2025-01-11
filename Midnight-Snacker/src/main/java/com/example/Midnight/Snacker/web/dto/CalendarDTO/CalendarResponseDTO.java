package com.example.Midnight.Snacker.web.dto.CalendarDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CalendarResponseDTO {
    private LocalDate date;

}
