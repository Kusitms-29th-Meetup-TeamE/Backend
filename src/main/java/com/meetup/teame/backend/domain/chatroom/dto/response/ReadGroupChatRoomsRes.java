package com.meetup.teame.backend.domain.chatroom.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
public class ReadGroupChatRoomsRes {
    private List<GroupChatRoomRes> groupChatRoomResList;

    public static ReadGroupChatRoomsRes of(List<GroupChatRoomRes> groupChatRooms) {
        return ReadGroupChatRoomsRes.builder()
                .groupChatRoomResList(groupChatRooms)
                .build();
    }
}
