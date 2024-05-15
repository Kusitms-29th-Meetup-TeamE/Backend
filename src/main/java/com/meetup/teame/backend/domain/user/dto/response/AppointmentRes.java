package com.meetup.teame.backend.domain.user.dto.response;

import com.meetup.teame.backend.domain.activity.entity.Activity;
import com.meetup.teame.backend.domain.chatroom.entity.Appointment;
import com.meetup.teame.backend.domain.chatroom.entity.DirectChatRoom;
import com.meetup.teame.backend.domain.chatroom.entity.GroupChatRoom;
import lombok.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
public class AppointmentRes {
    private LocalDate date;
    private String tag;
    private String description;
    private String about;

    public static AppointmentRes ofActivity(GroupChatRoom groupChatRoom) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("a h시 m분", new Locale("ko", "KR"));
        String startTime = groupChatRoom.getActivity().getTime().format(formatter);
        String endTIme = groupChatRoom.getActivity().getTime().plusMinutes(groupChatRoom.getActivity().getDuration()).format(formatter);
        return AppointmentRes.builder()
                .date(groupChatRoom.getActivity().getTime().toLocalDate())
                .tag(groupChatRoom.getActivity().getTitle())
                .description(groupChatRoom.getActivity().getTitle() + " 활동 참여하기")
                .about(groupChatRoom.getActivity().getLocation() + ", " + startTime + " ~ " + endTIme)
                .build();
    }

    public static AppointmentRes ofDirectChatRoom(DirectChatRoom directChatRoom) {
        return AppointmentRes.builder()
                .date(directChatRoom.getNextAppointment().getAppointmentDate())
                .tag(directChatRoom.getExperience().getDescription())
                .description(directChatRoom.getExperience().getDescription() + " 배움 나누기")
                .about(directChatRoom.getNextAppointment().getAppointmentLocation())
                .build();
    }

    public static AppointmentRes ofGroupChatRoom(GroupChatRoom groupChatRoom) {
        return AppointmentRes.builder()
                .date(groupChatRoom.getNextAppointment().getAppointmentDate())
                .tag(groupChatRoom.getActivity().getTitle())
                .description(groupChatRoom.getNextAppointment().getAppointmentDate() + " 약속")
                .about(groupChatRoom.getNextAppointment().getAppointmentLocation())
                .build();
    }
}
