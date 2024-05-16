package com.meetup.teame.backend.domain.chatroom.repository.custom;

import com.meetup.teame.backend.domain.chatroom.entity.DirectChatRoom;
import com.meetup.teame.backend.domain.chatroom.entity.GroupChatRoom;
import com.meetup.teame.backend.domain.user.entity.User;

import java.util.List;
import java.util.Optional;

public interface DirectChatRoomRepositoryCustom {
    Optional<DirectChatRoom> findByMentorAndMentee(User mentor, User mentee);

    List<DirectChatRoom> findForUser(User user);

    List<DirectChatRoom> findUpdatableRooms();

    List<DirectChatRoom> findAppointmentForUserInMonth(User who, int year, int month);
}
