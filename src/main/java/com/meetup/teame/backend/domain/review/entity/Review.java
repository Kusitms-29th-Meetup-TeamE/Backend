package com.meetup.teame.backend.domain.review.entity;

import com.meetup.teame.backend.domain.chatroom.entity.DirectChatRoom;
import com.meetup.teame.backend.domain.experience.entity.Experience;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@Comment("후기")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("내용")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentor")
    private Experience mentor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentee")
    private DirectChatRoom mentee;

    public static Review of(String description, Experience mentor, DirectChatRoom mentee) {
        return Review.builder()
                .description(description)
                .mentor(mentor)
                .mentee(mentee)
                .build();
    }
}
