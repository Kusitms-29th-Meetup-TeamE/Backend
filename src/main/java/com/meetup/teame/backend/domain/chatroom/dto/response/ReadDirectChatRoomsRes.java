package com.meetup.teame.backend.domain.chatroom.dto.response;

import com.meetup.teame.backend.domain.user.entity.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
public class ReadDirectChatRoomsRes {
    private Long myId;
    private List<DirectChatRoomRes> directChatRoomResList;

    public static ReadDirectChatRoomsRes of(User user, List<DirectChatRoomRes> directChatRooms) {
        return ReadDirectChatRoomsRes.builder()
                .myId(user.getId())
                .directChatRoomResList(directChatRooms)
                .build();
    }
}
