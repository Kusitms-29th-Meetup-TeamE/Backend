package com.meetup.teame.backend.domain.chatting.repository.custom;

import com.meetup.teame.backend.domain.chatting.entity.GroupChatRoom;
import com.meetup.teame.backend.domain.user.entity.User;

import java.util.List;

public interface GroupChatRoomRepositoryCustom {
    List<GroupChatRoom> findForUser(User user);

    List<GroupChatRoom> findUpdatableRooms();

    List<GroupChatRoom> findAppointmentForUserInMonth(User who, int year, int month);

    List<GroupChatRoom> findActivityForUserInMonth(User who, int year, int month);
}
