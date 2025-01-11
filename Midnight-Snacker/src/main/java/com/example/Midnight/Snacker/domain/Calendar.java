package com.example.Midnight.Snacker.domain;

import com.example.Midnight.Snacker.domain.common.BaseEntity;
import com.example.Midnight.Snacker.domain.enums.CategoryE;
import com.example.Midnight.Snacker.domain.enums.Color;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@DynamicUpdate
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Calendar extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; // id

    @Column
    private String imageUrl; //이미지

    @Column(nullable = false)
    private LocalDateTime date; //날짜

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Color color; //색깔

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoryE categoryE;

    @Column(nullable = false)
    private String detailFood; //상세 음식

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}
