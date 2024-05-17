package com.meetup.teame.backend.domain.review.entity;

import com.meetup.teame.backend.domain.chatroom.entity.Appointment;
import com.meetup.teame.backend.domain.chatroom.entity.DirectChatRoom;
import com.meetup.teame.backend.domain.experience.entity.Experience;
import com.meetup.teame.backend.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.time.LocalDate;

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
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentor_id")
    private User mentor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentee_id")
    private User mentee;

    @Embedded
    private Appointment appointment;

    @Comment("후기 작성 여부")
    private Boolean isWritten;

    public static Review of(User mentor, User mentee, Appointment appointment) {
        return Review.builder()
                .mentor(mentor)
                .mentee(mentee)
                .appointment(appointment)
                .isWritten(false)
                .build();
    }

    public void setContent(String content) {
        this.content = content;
    }
}
