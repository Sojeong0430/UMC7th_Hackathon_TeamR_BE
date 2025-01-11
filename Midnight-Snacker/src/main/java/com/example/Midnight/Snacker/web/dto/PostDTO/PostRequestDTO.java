package com.example.Midnight.Snacker.web.dto.PostDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class PostRequestDTO {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class addPostRequestDTO {
        private String title;
        private String body;
        private String imageUrl;
    }
}
