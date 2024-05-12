package com.meetup.teame.backend.domain.chatroom.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.meetup.teame.backend.domain.chatroom.entity.ChatRoom;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
public class DirectChatRoomRes {
    private Long id;

    private String imageUrl;

    private String opponent;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM월 dd일")
    private LocalDate appointmentDate;

    private LocalDateTime lastChatTime;

    private String lastMessage;

    private Boolean isMentor;

    public static DirectChatRoomRes of(ChatRoom chatRoom) {
        return DirectChatRoomRes.builder()
                .id(chatRoom.getId())
                .imageUrl(chatRoom.getImageUrl())
                .opponent(chatRoom.getTitle())
                .lastMessage(chatRoom.getLastMessage())
                .appointmentDate(chatRoom.getAppointmentDate().toLocalDate())
                .build();
    }
}
