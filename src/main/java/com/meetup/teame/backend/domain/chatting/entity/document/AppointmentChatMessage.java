package com.meetup.teame.backend.domain.chatting.entity.document;

import com.meetup.teame.backend.domain.chatting.entity.ChatMessageType;
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
    private AppointmentChatMessage(String chatRoomId, Long senderId, String senderName, String senderImageUrl, LocalDateTime createdAt,
                                   ExperienceType experienceType, LocalDateTime appointmentTime, String location) {
        super(chatRoomId, ChatMessageType.APPOINTMENT, senderId, senderName, senderImageUrl, createdAt);
        this.experienceType = experienceType;
        this.appointmentTime = appointmentTime;
        this.location = location;
    }

    public static AppointmentChatMessage of(String chatRoomId, Long senderId, String senderName, String senderImageUrl, LocalDateTime createdAt,
                                            String experienceType, LocalDateTime appointmentTime, String location) {
        return AppointmentChatMessage.builder()
                .chatRoomId(chatRoomId)
                .senderId(senderId)
                .senderName(senderName)
                .senderImageUrl(senderImageUrl)
                .createdAt(createdAt)
                .experienceType(ExperienceType.of(experienceType))
                .appointmentTime(appointmentTime)
                .location(location)
                .build();
    }

    @Override
    public String getMessage() {
        return "["+experienceType.getDescription()+"] 배움 나누기가 확정되었어요!";
    }
}
