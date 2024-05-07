package com.meetup.teame.backend.domain.chatroom.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.meetup.teame.backend.domain.chatroom.entity.ChatRoom;
import com.meetup.teame.backend.domain.chatroom.entity.ChatType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
public class GroupChatRoomRes {
    private Long id;

    private String imageUrl;

    private String title;

    private Long lastMeetingDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd (E)")
    private LocalDate appointmentDate;

    public static GroupChatRoomRes of(ChatRoom chatRoom) {
        return GroupChatRoomRes.builder()
                .id(chatRoom.getId())
                .imageUrl(chatRoom.getImageUrl())
                .title(chatRoom.getTitle())
                .lastMeetingDate(ChronoUnit.DAYS.between(chatRoom.getLastMeetingDate(), LocalDate.now()))
                .appointmentDate(chatRoom.getAppointmentDate().toLocalDate())
                .build();
    }
}
