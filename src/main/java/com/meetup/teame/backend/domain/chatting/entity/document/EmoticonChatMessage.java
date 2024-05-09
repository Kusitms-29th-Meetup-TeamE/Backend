package com.meetup.teame.backend.domain.chatting.entity.document;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.TypeAlias;

import java.time.LocalDateTime;

@TypeAlias("EmoticonChatMessage")
@Getter
public class EmoticonChatMessage extends ChatMessage {
    private final String emoticon;

    @Builder
    protected EmoticonChatMessage(Long chatRoomId, Long senderId, LocalDateTime createdAt, String emoticon) {
        super(chatRoomId, senderId, createdAt);
        this.emoticon = emoticon;
    }

    public static EmoticonChatMessage of(Long chatRoomId, Long senderId, LocalDateTime createdAt, String emoticon) {
        return EmoticonChatMessage.builder()
                .chatRoomId(chatRoomId)
                .senderId(senderId)
                .createdAt(createdAt)
                .emoticon(emoticon)
                .build();
    }
}
