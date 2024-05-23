package com.meetup.teame.backend.domain.chatting.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.meetup.teame.backend.domain.chatting.entity.ChatMessageType;
import com.meetup.teame.backend.domain.chatting.entity.document.AppointmentChatMessage;
import com.meetup.teame.backend.domain.chatting.entity.document.ChatMessage;
import com.meetup.teame.backend.domain.chatting.entity.document.EmoticonChatMessage;
import com.meetup.teame.backend.domain.chatting.entity.document.TextChatMessage;
import com.meetup.teame.backend.domain.experience.entity.ExperienceType;
import com.meetup.teame.backend.domain.user.entity.User;
import com.meetup.teame.backend.global.exception.CustomException;
import com.meetup.teame.backend.global.exception.ExceptionContent;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Optional;

import static java.time.format.DateTimeFormatter.ofPattern;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class ChatMessageRes {
    private ChatMessageType type;

    private String createdAt;

    private String text;//일반 메세지용

    private String emoticon;//이모티콘 메세지용

    private String experienceType;//약속 메세지용

    private String appointmentTime;//약속 메세지용

    private String location;//약속 메세지용

    private Long senderId;

    private String senderName;

    private String senderImageUrl;

    private static ChatMessageRes ofText(TextChatMessage textChatMessage) {
        return ChatMessageRes.builder()
                .type(ChatMessageType.TEXT)
                .createdAt(textChatMessage.getCreatedAt()
                        .format(ofPattern("yyyy년 MM월 dd일 a h시 m분", new Locale("ko", "KR"))))
                .senderId(textChatMessage.getSenderId())
                .senderName(textChatMessage.getSenderName())
                .senderImageUrl(textChatMessage.getSenderImageUrl())
                .text(textChatMessage.getText())
                .build();
    }

    private static ChatMessageRes ofEmoticon(EmoticonChatMessage emoticonChatMessage) {
        return ChatMessageRes.builder()
                .type(ChatMessageType.EMOTICON)
                .createdAt(emoticonChatMessage.getCreatedAt()
                        .format(ofPattern("yyyy년 MM월 dd일 a h시 m분", new Locale("ko", "KR"))))
                .senderId(emoticonChatMessage.getSenderId())
                .senderName(emoticonChatMessage.getSenderName())
                .senderImageUrl(emoticonChatMessage.getSenderImageUrl())
                .emoticon(emoticonChatMessage.getEmoticon())
                .build();
    }

    private static ChatMessageRes ofAppointment(AppointmentChatMessage appointmentChatMessage) {

        return ChatMessageRes.builder()
                .type(ChatMessageType.APPOINTMENT)
                .createdAt(appointmentChatMessage.getCreatedAt()
                        .format(ofPattern("yyyy년 MM월 dd일 a h시 m분", new Locale("ko", "KR"))))
                .senderId(appointmentChatMessage.getSenderId())
                .senderName(appointmentChatMessage.getSenderName())
                .senderImageUrl(appointmentChatMessage.getSenderImageUrl())
                .experienceType(Optional.ofNullable(appointmentChatMessage.getExperienceType())
                        .map(ExperienceType::getDescription)
                        .orElse(null))
                .appointmentTime(appointmentChatMessage.getAppointmentTime()
                        .format(ofPattern("yyyy년 MM월 dd일 EEEE", new Locale("ko", "KR"))))
                .location(appointmentChatMessage.getLocation())
                .build();
    }

    public static ChatMessageRes of(ChatMessage chatMessage) {
        return switch (chatMessage.getType()) {
            case TEXT -> ofText((TextChatMessage) chatMessage);
            case EMOTICON -> ofEmoticon((EmoticonChatMessage) chatMessage);
            case APPOINTMENT -> ofAppointment((AppointmentChatMessage) chatMessage);
        };
    }
}
