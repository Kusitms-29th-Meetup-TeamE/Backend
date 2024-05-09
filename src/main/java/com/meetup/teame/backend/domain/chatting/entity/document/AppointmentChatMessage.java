package com.meetup.teame.backend.domain.chatting.entity.document;

import com.meetup.teame.backend.domain.experience.entity.ExperienceType;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.TypeAlias;

import java.time.LocalDateTime;

@TypeAlias("AppointmentChatMessage")
@Getter
public class AppointmentChatMessage extends ChatMessage {
    private final ExperienceType experienceType;

    private final LocalDateTime appointmentTime;

    private final String location;

    @Builder
    protected AppointmentChatMessage(Long chatRoomId, Long senderId, LocalDateTime createdAt, ExperienceType experienceType, LocalDateTime appointmentTime, String location) {
        super(chatRoomId, senderId, createdAt);
        this.experienceType = experienceType;
        this.appointmentTime = appointmentTime;
        this.location = location;
    }

    public static AppointmentChatMessage of(Long chatRoomId, Long senderId, LocalDateTime createdAt, String experienceType, LocalDateTime appointmentTime, String location) {
        return AppointmentChatMessage.builder()
                .chatRoomId(chatRoomId)
                .senderId(senderId)
                .createdAt(createdAt)
                .experienceType(ExperienceType.of(experienceType))
                .appointmentTime(appointmentTime)
                .location(location)
                .build();
    }
}
