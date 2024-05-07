package com.meetup.teame.backend.domain.chatting.dto.response;

import com.meetup.teame.backend.domain.chatting.entity.ChatMessageType;
import com.meetup.teame.backend.domain.experience.entity.ExperienceType;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class ChatMessageRes {
    private ChatMessageType type;

    private LocalDateTime createdAt;

    private String text;//일반 메세지용

    private String emoticon;//이모티콘 메세지용 ,todo 추후에 enum으로 바꿀지 고민

    private ExperienceType experienceType;//약속 메세지용

    private LocalDateTime appointmentTime;//약속 메세지용

    private String location;//약속 메세지용

    private String senderName;

    private String senderImageUrl;

    public static ChatMessageRes ofText(LocalDateTime createdAt, String senderName, String senderImageUrl, String text) {
        return ChatMessageRes.builder()
                .type(ChatMessageType.TEXT)
                .createdAt(createdAt)
                .senderName(senderName)
                .senderImageUrl(senderImageUrl)
                .text(text)
                .build();
    }

    public static ChatMessageRes ofEmoticon(LocalDateTime createdAt, String senderName, String senderImageUrl, String emoticon) {
        return ChatMessageRes.builder()
                .type(ChatMessageType.EMOTICON)
                .createdAt(createdAt)
                .senderName(senderName)
                .senderImageUrl(senderImageUrl)
                .emoticon(emoticon)
                .build();
    }

    public static ChatMessageRes ofAppointment(LocalDateTime createdAt, String senderName, String senderImageUrl, ExperienceType experienceType, LocalDateTime appointmentTime, String location) {
        return ChatMessageRes.builder()
                .type(ChatMessageType.APPOINTMENT)
                .createdAt(createdAt)
                .senderName(senderName)
                .senderImageUrl(senderImageUrl)
                .experienceType(experienceType)
                .appointmentTime(appointmentTime)
                .location(location)
                .build();
    }
}
