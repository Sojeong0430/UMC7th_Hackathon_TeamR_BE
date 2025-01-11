package com.example.Midnight.Snacker.web.dto.PostDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@Setter
public class PostInfoDTO {
    private final Long postId;
    private final String title;
    private final String body;
    private final LocalDate date;
    private final String imageUrl;
    private final int commentCount;
}
