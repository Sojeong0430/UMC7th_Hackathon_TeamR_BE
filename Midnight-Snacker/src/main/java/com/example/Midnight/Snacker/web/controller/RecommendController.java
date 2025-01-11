package com.example.Midnight.Snacker.web.controller;

import com.example.Midnight.Snacker.apiPayload.ApiResponse;
import com.example.Midnight.Snacker.apiPayload.code.status.SuccessStatus;
import com.example.Midnight.Snacker.service.RecommendService.RecommendService;
import com.example.Midnight.Snacker.web.dto.MemberDTO.AuthRequestDTO;
import com.example.Midnight.Snacker.web.dto.MemberDTO.AuthResponseDTO;
import com.example.Midnight.Snacker.web.dto.recommendDTO.RecommendResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RecommendController {

    private final RecommendService recommendService;

    @ResponseStatus(code = HttpStatus.OK)
    @Operation(
            summary = "추천 API",
            description = "chatGPT로부터 받는 추천 API입니다.")
    @GetMapping("/api/recommend")
    public ApiResponse<RecommendResponseDTO.RecommendationResultDTO> getRecommendations() {
        RecommendResponseDTO.RecommendationResultDTO recommendations = recommendService.getChatGPTResponse();
        return ApiResponse.of(SuccessStatus.RECOMMEND_OK, recommendations);
    }


}
