package com.example.Midnight.Snacker.service.RecommendService;

import com.example.Midnight.Snacker.domain.enums.Category;
import com.example.Midnight.Snacker.domain.enums.Color;
import com.example.Midnight.Snacker.web.dto.recommendDTO.RecommendResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;


@Service
@AllArgsConstructor
public class RecommendServiceImpl implements RecommendService{

    private final ObjectMapper objectMapper;

    public String createPrompt() {
        return String.format(
                "저는 오후 10시에 먹을 야식을 찾고 있습니다. 건강한 야식(백)과 건강하지 않은 야식(흑)을 각각 한 개씩 추천해주세요. {한식, 중식, 일식, 양식, 아시안, 패스트푸드, 분식, 디저트, 고기} 카테고리 내에서 추천해주세요. 한국말로 추천해주세요. 그리고 각각 그 메뉴의 설명을 4가지씩 추가해주는데, 13자 이내로 작성해주세요. 메뉴는 아래의 형식으로 추천해주세요 : [{'흑', '메뉴명', '메뉴가 해당하는 카테고리', '설명1', '설명2', '설명3', '설명4'},{'백', '메뉴명', '메뉴가 해당하는 카테고리','설명1', '설명2', '설명3', '설명4'}]"
        );
    }

    @Override
    public RecommendResponseDTO.RecommendationResultDTO getChatGPTResponse() {
        String prompt = createPrompt();

        // ChatGPT API 호출
        String response = chatGPT(prompt);

        // 결과 파싱
        return parseResponse(response);
    }

    @Override
    public String chatGPT(String prompt) {
        String url = System.getenv("GPT_URL");
        String apiKey = System.getenv("GPT_KEY");
        String model = "gpt-3.5-turbo";

        RestTemplate restTemplate = new RestTemplate();
        org.springframework.http.HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        String jsonBody = String.format(
                "{\"model\": \"%s\", \"messages\": [{\"role\": \"user\", \"content\": \"%s\"}]}",
                model, prompt.replaceAll("\"", "\\\\\"")
        );

        HttpEntity<String> entity = new HttpEntity<>(jsonBody, headers);
        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
            return extractMessageFromJSONResponse(response.getBody());

        } catch (Exception e) {
            e.printStackTrace();
            return "Error connecting to OpenAI: " + e.getMessage();
        }

    }

    public String extractMessageFromJSONResponse(String response) {
        try {
            Map<String, Object> map = objectMapper.readValue(response, new TypeReference<>() {});
            List<Map<String, Object>> choices = (List<Map<String, Object>>) map.get("choices");

            return choices.stream()
                    .map(choice -> (String) ((Map<String, Object>) choice.get("message")).get("content"))
                    .collect(Collectors.joining("\n"));
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse response: " + e.getMessage(), e);
        }
    }



    private RecommendResponseDTO.RecommendationResultDTO parseResponse(String response) {
        try {
            // 응답 문자열을 리스트로 변환
            response = response.replaceAll("[\\[\\]{}']", ""); // 중괄호, 대괄호, 작은 따옴표 제거
            String[] rawItems = response.split("\\n"); // 각각의 아이템 분리

            // BLACK과 WHITE로 분리
            List<RecommendResponseDTO.RecommendationDTO> blackList = new ArrayList<>();
            List<RecommendResponseDTO.RecommendationDTO> whiteList = new ArrayList<>();

            for (String item : rawItems) {
                String[] elements = item.split(",\\s*"); // 각 필드 분리
                String type = elements[0].trim(); // 흑 or 백
                String menu = elements[1].trim(); // 메뉴
                String category = elements[2].trim(); // 카테고리
                List<String> descriptions = Arrays.asList(elements[3], elements[4], elements[5], elements[6]); // 설명 리스트

                RecommendResponseDTO.RecommendationDTO dto = RecommendResponseDTO.RecommendationDTO.builder()
                        .type(convertToColor(type))
                        .menu(menu)
                        .category(Category.valueOf(category))
                        .description(descriptions)
                        .build();

                if (dto.getType() == Color.BLACK) {
                    blackList.add(dto);
                } else if (dto.getType() == Color.WHITE) {
                    whiteList.add(dto);
                }
            }

            return RecommendResponseDTO.RecommendationResultDTO.builder()
                    .black(blackList)
                    .white(whiteList)
                    .build();

        } catch (Exception e) {
            throw new RuntimeException("Failed to parse response: " + e.getMessage(), e);
        }
    }





    private Color convertToColor(String value) {
        if ("흑".equals(value)) {
            return Color.BLACK;
        } else if ("백".equals(value)) {
            return Color.WHITE;
        } else {
            throw new IllegalArgumentException("Invalid color value: " + value);
        }
    }
}
