package com.meetup.teame.backend.domain.chatroom.repository.custom;

import com.meetup.teame.backend.domain.chatroom.entity.DirectChatRoom;
import com.meetup.teame.backend.domain.chatroom.entity.GroupChatRoom;
import com.meetup.teame.backend.domain.user.entity.User;

import java.util.List;

public interface GroupChatRoomRepositoryCustom {
    List<GroupChatRoom> findForUser(User user);

    List<GroupChatRoom> findUpdatableRooms();

    List<GroupChatRoom> findAppointmentForUserInMonth(User who, int year, int month);

    List<GroupChatRoom> findActivityForUserInMonth(User who, int year, int month);
}
