package com.example.Midnight.Snacker.service.calenderCountService;

import com.example.Midnight.Snacker.domain.Calendar;
import com.example.Midnight.Snacker.domain.Member;
import com.example.Midnight.Snacker.web.dto.calenderCountDTO.calenderCountResponseDTO;

import java.util.List;

public interface CalenderCountService {
    public List<Calendar> getCalenderList(Member member);
    public List<calenderCountResponseDTO.monthCountPreviewDTO> getMonthCountPreviewDTOList();
    public void categoryCount(List<calenderCountResponseDTO.monthCountPreviewDTO> countPreviewDTOList,List<Calendar> member30CalenderList);
}
