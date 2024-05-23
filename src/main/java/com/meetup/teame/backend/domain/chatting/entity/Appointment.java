package com.meetup.teame.backend.domain.chatting.entity;

import jakarta.persistence.Embeddable;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.time.LocalDate;


@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
public class Appointment {
    @Comment("약속 시간")
    private LocalDate appointmentDate;

    @Comment("약속 장소")
    private String appointmentLocation;

    public static Appointment of(LocalDate appointmentDate, String appointmentLocation) {
        return Appointment.builder()
                .appointmentDate(appointmentDate)
                .appointmentLocation(appointmentLocation)
                .build();
    }
}
