package com.meetup.teame.backend.domain.experience.entity;

import com.meetup.teame.backend.domain.chatroom.entity.DirectChatRoom;
import com.meetup.teame.backend.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

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

    @Comment("경험 설명")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "experience", cascade = CascadeType.ALL)
    private List<DirectChatRoom> directChatRooms;

    public static Experience of(ExperienceType type, String description, User user) {
        return Experience.builder()
                .type(type)
                .description(description)
                .user(user)
                .build();
    }
}
