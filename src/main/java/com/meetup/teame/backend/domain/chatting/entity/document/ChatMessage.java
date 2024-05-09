package com.meetup.teame.backend.domain.chatting.entity.document;

import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "chat_message")
@TypeAlias("ChatMessage")
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public abstract class ChatMessage {
    @Id
    private String id;
    private final Long chatRoomId;
    private final Long senderId;
    private final LocalDateTime createdAt;
}
