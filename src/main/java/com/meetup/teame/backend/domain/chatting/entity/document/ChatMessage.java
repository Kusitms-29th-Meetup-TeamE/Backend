package com.meetup.teame.backend.domain.chatting.entity.document;

import com.meetup.teame.backend.domain.chatting.entity.ChatMessageType;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "chat_message")
@TypeAlias("ChatMessage")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class ChatMessage {
    @Id
    private String id;
    private String chatRoomId;
    private ChatMessageType type;
    private Long senderId;
    private String senderName;
    private String senderImageUrl;
    private LocalDateTime createdAt;

    protected ChatMessage(String chatRoomId, ChatMessageType type, Long senderId, String senderName, String senderImageUrl, LocalDateTime createdAt) {
        this.chatRoomId = chatRoomId;
        this.type = type;
        this.senderId = senderId;
        this.senderName = senderName;
        this.senderImageUrl = senderImageUrl;
        this.createdAt = createdAt;
    }
}
