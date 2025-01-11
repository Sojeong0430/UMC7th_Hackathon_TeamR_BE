package com.example.Midnight.Snacker.service.calenderCountService;

import com.example.Midnight.Snacker.domain.Calendar;
import com.example.Midnight.Snacker.domain.Category;
import com.example.Midnight.Snacker.domain.Member;
import com.example.Midnight.Snacker.domain.enums.CategoryE;
import com.example.Midnight.Snacker.repository.CalendarRepository;
import com.example.Midnight.Snacker.web.dto.calenderCountDTO.calenderCountResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CalenderCountServiceImpl implements CalenderCountService {

    private final CalendarRepository calendarRepository;

    @Override
    public List<Calendar> getCalenderList (Member member){
        List<Calendar> CalList = calendarRepository.findAllByMember(member);

        // 오늘 날짜
        LocalDateTime thirtyDaysAgo = LocalDateTime.now().minusDays(30);

        // 30일 이내 데이터 필터링
        List<Calendar> filteredList = CalList.stream()
                .filter(calendar -> calendar.getCreatedAt().isAfter(thirtyDaysAgo)) // 생성일이 30일 이전인 데이터 제외
                .collect(Collectors.toList());

        return filteredList;
    }

    @Override
    public List<calenderCountResponseDTO.monthCountPreviewDTO> getMonthCountPreviewDTOList() {
        List<calenderCountResponseDTO.monthCountPreviewDTO> countList = new ArrayList<>();

        List<CategoryE> menuList = Arrays.asList(CategoryE.한식, CategoryE.중식, CategoryE.일식, CategoryE.양식, CategoryE.아시안, CategoryE.패스트푸드, CategoryE.카페, CategoryE.고기);
        for (int i = 0; i < menuList.size(); i++) {
            calenderCountResponseDTO.monthCountPreviewDTO cm = new calenderCountResponseDTO.monthCountPreviewDTO();
            cm.setCategory(menuList.get(i));
            cm.setCount(0);
            countList.add(cm);
        }
        return countList;
    }

    @Override
    public void categoryCount(List<calenderCountResponseDTO.monthCountPreviewDTO> countPreviewDTOList,List<Calendar> member30CalenderList ){
        member30CalenderList.stream()
                .forEach(calendar -> {
                    CategoryE category = calendar.getCategoryE();

                    countPreviewDTOList.stream()
                            .filter(cm -> cm.getCategory().equals(category))
                            .forEach(cm -> cm.setCount(cm.getCount() + 1));
                });
    }

}
