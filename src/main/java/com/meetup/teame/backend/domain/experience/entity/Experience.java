package com.meetup.teame.backend.domain.experience.entity;

import com.meetup.teame.backend.domain.chatting.entity.DirectChatRoom;
import com.meetup.teame.backend.domain.user.dto.request.MyExperienceReq;
import com.meetup.teame.backend.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@Comment("경험")
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("경험 유형")
    @Enumerated(EnumType.STRING)
    private ExperienceType type;

    @Comment("경험 제목")
    private String description;

    @Comment("경험 상세 내용")
    private String detail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private Long reviewCount;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "experience", cascade = CascadeType.ALL)
    private List<DirectChatRoom> directChatRooms;

    public static Experience of(String title, String experienceType, String detail, User user) {
        return Experience.builder()
                .description(title)
                .type(ExperienceType.of(experienceType))
                .detail(detail)
                .user(user)
                .reviewCount(0L)
                .build();
    }

    public void incrementReviewCount() {
        this.reviewCount++;
    }
}
