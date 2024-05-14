package com.meetup.teame.backend.domain.chatroom.repository;

import com.meetup.teame.backend.domain.activity.entity.Activity;
import com.meetup.teame.backend.domain.chatroom.entity.GroupChatRoom;
import com.meetup.teame.backend.domain.chatroom.repository.custom.GroupChatRoomRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GroupChatRoomRepository extends JpaRepository<GroupChatRoom, Long>, GroupChatRoomRepositoryCustom {
    Optional<GroupChatRoom> findByActivityId(Long activityId);
}
