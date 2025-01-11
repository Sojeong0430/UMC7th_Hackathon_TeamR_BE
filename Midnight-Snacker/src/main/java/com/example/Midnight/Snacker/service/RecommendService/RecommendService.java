package com.example.Midnight.Snacker.service.RecommendService;

import com.example.Midnight.Snacker.web.dto.recommendDTO.RecommendResponseDTO;

import java.util.List;

public interface RecommendService {
    String createPrompt();
    String chatGPT(String prompt);
    RecommendResponseDTO.RecommendationResultDTO getChatGPTResponse();
}
