package com.meetup.teame.backend.domain.chatting.entity.document;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.TypeAlias;

import java.time.LocalDateTime;

@TypeAlias("TextChatMessage")
@Getter
public class TextChatMessage extends ChatMessage {
    private final String text;

    @Builder
    private TextChatMessage(Long chatRoomId, Long senderId, LocalDateTime createdAt, String text) {
        super(chatRoomId, senderId, createdAt);
        this.text = text;
    }

    public static TextChatMessage of(Long chatRoomId, Long senderId, LocalDateTime createdAt, String text) {
        return TextChatMessage.builder()
                .chatRoomId(chatRoomId)
                .senderId(senderId)
                .createdAt(createdAt)
                .text(text)
                .build();
    }
}
