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
public class ReadGroupChatRoomsRes {
    private Long myId;
    private List<GroupChatRoomRes> groupChatRoomResList;

    public static ReadGroupChatRoomsRes of(User user, List<GroupChatRoomRes> groupChatRooms) {
        return ReadGroupChatRoomsRes.builder()
                .myId(user.getId())
                .groupChatRoomResList(groupChatRooms)
                .build();
    }
}
