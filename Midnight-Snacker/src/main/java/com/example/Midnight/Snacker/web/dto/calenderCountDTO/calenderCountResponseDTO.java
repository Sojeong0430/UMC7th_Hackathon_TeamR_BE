package com.example.Midnight.Snacker.web.dto.calenderCountDTO;

import com.example.Midnight.Snacker.domain.enums.CategoryE;
import lombok.*;

import java.util.List;

public class calenderCountResponseDTO {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class monthCountPreviewDTO {
        private CategoryE category;
        private Integer count;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class MissionPreviewListDTO {
        private List<monthCountPreviewDTO> monthCountPreviewDTOList;
    }
}
