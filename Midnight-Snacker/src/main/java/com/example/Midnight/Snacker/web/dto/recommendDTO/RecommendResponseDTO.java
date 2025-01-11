package com.example.Midnight.Snacker.web.dto.recommendDTO;

import com.example.Midnight.Snacker.domain.enums.Category;
import com.example.Midnight.Snacker.domain.enums.Color;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class RecommendResponseDTO {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RecommendationDTO {
        private Color type;
        private String menu;
        private Category category;
        private List<String> description;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RecommendationResultDTO {
        private List<RecommendationDTO> white;
        private List<RecommendationDTO> black;
    }
}
