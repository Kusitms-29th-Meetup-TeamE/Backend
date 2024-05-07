package com.meetup.teame.backend.domain.chatroom.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
public class ReadDirectChatRoomsRes {
    private List<DirectChatRoomRes> directChatRoomResList;

    public static ReadDirectChatRoomsRes of(List<DirectChatRoomRes> directChatRooms) {
        return ReadDirectChatRoomsRes.builder()
                .directChatRoomResList(directChatRooms)
                .build();
    }
}
