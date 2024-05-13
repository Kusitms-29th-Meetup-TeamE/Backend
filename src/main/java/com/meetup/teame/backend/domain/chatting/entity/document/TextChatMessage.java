package com.meetup.teame.backend.domain.chatting.entity.document;

import com.meetup.teame.backend.domain.chatting.entity.ChatMessageType;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.TypeAlias;

import java.time.LocalDateTime;

@TypeAlias("TextChatMessage")
@Getter
public class TextChatMessage extends ChatMessage {
    private final String text;

    @Builder
    private TextChatMessage(String chatRoomId, Long senderId, String senderName, String senderImageUrl, LocalDateTime createdAt,
                            String text) {
        super(chatRoomId, ChatMessageType.TEXT, senderId, senderName, senderImageUrl, createdAt);
        this.text = text;
    }

    public static TextChatMessage of(String chatRoomId, Long senderId, String senderName, String senderImageUrl, LocalDateTime createdAt,
                                     String text) {
        return TextChatMessage.builder()
                .chatRoomId(chatRoomId)
                .senderId(senderId)
                .senderName(senderName)
                .senderImageUrl(senderImageUrl)
                .createdAt(createdAt)
                .text(text)
                .build();
    }

    @Override
    public String getMessage() {
        return text;
    }
}
