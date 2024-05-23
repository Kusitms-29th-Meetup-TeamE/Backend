package com.meetup.teame.backend.domain.chatting.repository;

import com.meetup.teame.backend.domain.chatting.entity.GroupChatRoom;
import com.meetup.teame.backend.domain.chatting.repository.custom.GroupChatRoomRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GroupChatRoomRepository extends JpaRepository<GroupChatRoom, Long>, GroupChatRoomRepositoryCustom {
    Optional<GroupChatRoom> findByActivityId(Long activityId);
}
