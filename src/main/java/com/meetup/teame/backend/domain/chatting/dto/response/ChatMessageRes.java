package com.meetup.teame.backend.domain.chatting.dto.response;

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

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class ChatMessageRes {
    private ChatMessageType type;

    private LocalDateTime createdAt;

    private String text;//일반 메세지용

    private String emoticon;//이모티콘 메세지용 todo 추후에 enum으로 바꿀지 고민

    private String experienceType;//약속 메세지용

    private LocalDateTime appointmentTime;//약속 메세지용

    private String location;//약속 메세지용

    private String senderName;

    private String senderImageUrl;

    private static ChatMessageRes ofText(TextChatMessage textChatMessage) {
        return ChatMessageRes.builder()
                .type(ChatMessageType.TEXT)
                .createdAt(textChatMessage.getCreatedAt())
                .senderName(textChatMessage.getSenderName())
                .senderImageUrl(textChatMessage.getSenderImageUrl())
                .text(textChatMessage.getText())
                .build();
    }

    private static ChatMessageRes ofEmoticon(EmoticonChatMessage emoticonChatMessage) {
        return ChatMessageRes.builder()
                .type(ChatMessageType.EMOTICON)
                .createdAt(emoticonChatMessage.getCreatedAt())
                .senderName(emoticonChatMessage.getSenderName())
                .senderImageUrl(emoticonChatMessage.getSenderImageUrl())
                .emoticon(emoticonChatMessage.getEmoticon())
                .build();
    }

    private static ChatMessageRes ofAppointment(AppointmentChatMessage appointmentChatMessage) {
        return ChatMessageRes.builder()
                .type(ChatMessageType.APPOINTMENT)
                .createdAt(appointmentChatMessage.getCreatedAt())
                .senderName(appointmentChatMessage.getSenderName())
                .senderImageUrl(appointmentChatMessage.getSenderImageUrl())
                .experienceType(appointmentChatMessage.getExperienceType().getDescription())
                .appointmentTime(appointmentChatMessage.getAppointmentTime())
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
