package com.meetup.teame.backend.domain.chatroom.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.meetup.teame.backend.domain.chatroom.entity.Appointment;
import com.meetup.teame.backend.domain.chatroom.entity.DirectChatRoom;
import com.meetup.teame.backend.domain.chatting.entity.document.ChatMessage;
import com.meetup.teame.backend.domain.user.entity.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Optional;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
public class DirectChatRoomRes {
    private Long id;

    private String imageUrl;

    private String opponentName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM월 dd일")
    private LocalDate appointmentDate;

    private String lastChatTime;

    private String lastMessage;

    private Boolean isMentor;

    private String experienceType;

    public static DirectChatRoomRes of(DirectChatRoom chatRoom, User me, ChatMessage lastChatMessage) {
        User opponent;
        boolean isMentor = me.getId().equals(chatRoom.getExperience().getUser().getId());
        if (isMentor)
            opponent = chatRoom.getMentee();//내가 멘토일 경우 멘티를 가져옴
        else
            opponent = chatRoom.getExperience().getUser();//내가 멘티일 경우 멘토를 가져옴

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

        return DirectChatRoomRes.builder()
                .id(chatRoom.getId())
                .imageUrl(opponent.getImageUrl())
                .opponentName(opponent.getName())
                .appointmentDate(appointmentDate)
                .lastMessage(lastMessage)
                .lastChatTime(lastChatTime)
                .isMentor(isMentor)
                .experienceType(chatRoom.getExperience().getType().getDescription())
                .build();
    }
}
