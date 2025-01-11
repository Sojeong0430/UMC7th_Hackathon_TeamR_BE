package com.example.Midnight.Snacker.domain;

import com.example.Midnight.Snacker.domain.common.BaseEntity;
import com.example.Midnight.Snacker.domain.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@DynamicUpdate
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; // id

    @Column(nullable = false, length = 20)
    private String nickname; // 이름

    @Column(nullable = false)
    private String email; //이메일

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "VARCHAR(15) DEFAULT 'ACTIVE'")
    private Status status; //상태

    private LocalDate inactiveDate; //비활성 일 수

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer blackCount; //블랙 카운트

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer whiteCount; //화이트 카운트

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer totalCount; // 전체 합계

    private String accessToken; //액세스 토큰

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Calender> memberCalenderList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<CategoryCount> membercategoryCountList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Comment> comments = new ArrayList<>();
}
