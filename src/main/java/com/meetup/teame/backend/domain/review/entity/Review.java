package com.meetup.teame.backend.domain.review.entity;

import com.meetup.teame.backend.domain.chatroom.entity.Appointment;
import com.meetup.teame.backend.domain.chatroom.entity.DirectChatRoom;
import com.meetup.teame.backend.domain.experience.entity.Experience;
import com.meetup.teame.backend.domain.experience.entity.ExperienceType;
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

    @Comment("경험 상세 내용")
    private String appointmentDetail;

    @Enumerated(EnumType.STRING)
    private ExperienceType appointmentType;

    @Comment("경험 제목")
    private String appointmentTitle;

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

    private LocalDate reviewDate;

    public static Review of(Experience experience, User mentor, User mentee, Appointment appointment) {
        return Review.builder()
                .appointmentDetail(experience.getDetail())
                .appointmentType(experience.getType())
                .appointmentTitle(experience.getDescription())
                .mentor(mentor)
                .mentee(mentee)
                .appointment(appointment)
                .isWritten(false)
                .reviewDate(LocalDate.now())
                .build();
    }

    public void setContent(String content) {
        this.content = content;
        this.isWritten=true;
    }
}
