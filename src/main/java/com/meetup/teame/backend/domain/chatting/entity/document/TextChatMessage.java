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
    private TextChatMessage(Long senderId, LocalDateTime createdAt, String text) {
        super(senderId, createdAt);
        this.text = text;
    }

    public static TextChatMessage of(Long senderId, LocalDateTime createdAt, String text) {
        return TextChatMessage.builder()
                .senderId(senderId)
                .createdAt(createdAt)
                .text(text)
                .build();
    }
}
