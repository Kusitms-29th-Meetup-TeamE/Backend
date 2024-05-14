package com.meetup.teame.backend.domain.chatroom.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.meetup.teame.backend.domain.chatroom.entity.Appointment;
import com.meetup.teame.backend.domain.chatroom.entity.ChatRoom;
import com.meetup.teame.backend.domain.chatroom.entity.GroupChatRoom;
import com.meetup.teame.backend.domain.chatting.entity.document.ChatMessage;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.Optional;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
public class GroupChatRoomRes {
    private Long id;

    private String imageUrl;

    private String title;

    private Long lastMeetingDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM월 dd일")
    private LocalDate appointmentDate;

    private String lastChatTime;

    private String lastMessage;

    public static GroupChatRoomRes of(GroupChatRoom chatRoom, ChatMessage lastChatMessage) {
        Long lastMeetingDate = Optional.ofNullable(chatRoom.getLastAppointment())
                .map(Appointment::getAppointmentDate)
                .map(date -> ChronoUnit.DAYS.between(date, LocalDate.now()))
                .orElse(null);

        LocalDate appointmentDate = Optional.ofNullable(chatRoom.getNextAppointment())
                .map(Appointment::getAppointmentDate)
                .orElse(null);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("a h시 m분", new Locale("ko", "KR"));
        String lastChatTime = Optional.ofNullable(lastChatMessage)
                .map(chatMessage -> chatMessage.getCreatedAt().format(formatter))
                .orElse(null);

        String lastMessage = Optional.ofNullable(lastChatMessage)
                .map(ChatMessage::getMessage)
                .orElse(null);

        return GroupChatRoomRes.builder()
                .id(chatRoom.getId())
                .imageUrl(chatRoom.getActivity().getImageUrl())
                .title(chatRoom.getActivity().getTitle())
                .lastMeetingDate(lastMeetingDate)
                .appointmentDate(appointmentDate)
                .lastChatTime(lastChatTime)
                .lastMessage(lastMessage)
                .build();
    }
}
