package com.meetup.teame.backend.domain.chatroom.repository.custom;

import com.meetup.teame.backend.domain.chatroom.entity.ChatRoom;
import com.meetup.teame.backend.domain.chatroom.entity.ChatType;
import com.meetup.teame.backend.domain.user.entity.User;

import java.util.List;

public interface ChatRoomRepositoryCustom {
    List<ChatRoom> findChatRoomsForUser(User who, ChatType chatType);
}
