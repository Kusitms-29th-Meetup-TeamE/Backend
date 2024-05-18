package com.meetup.teame.backend.domain.user.dto.response;

import com.meetup.teame.backend.domain.chatroom.entity.DirectChatRoom;
import com.meetup.teame.backend.domain.chatroom.entity.GroupChatRoom;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
public class ReadCalenderRes {
    List<AppointmentRes> appointments;

    public static ReadCalenderRes of(List<GroupChatRoom> groupChatRoomsByActivity, List<DirectChatRoom> directChatRooms, List<GroupChatRoom> groupChatRooms) {
        List<AppointmentRes> appointments = new ArrayList<>();
        appointments.addAll(groupChatRoomsByActivity.stream()
                .map(AppointmentRes::ofActivity)
                .toList());
        appointments.addAll(directChatRooms.stream()
                .map(AppointmentRes::ofDirectChatRoom)
                .toList());
        appointments.addAll(groupChatRooms.stream()
                .map(AppointmentRes::ofGroupChatRoom)
                .toList());
        appointments.sort((a, b) -> a.getDate().compareTo(b.getDate()));

        return ReadCalenderRes.builder()
                .appointments(appointments)
                .build();
    }
}
