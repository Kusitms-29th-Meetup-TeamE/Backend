package com.meetup.teame.backend.domain.chatroom.repository;

import com.meetup.teame.backend.domain.chatroom.entity.DirectChatRoom;
import com.meetup.teame.backend.domain.chatroom.repository.custom.DirectChatRoomRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DirectChatRoomRepository extends JpaRepository<DirectChatRoom, Long>, DirectChatRoomRepositoryCustom {
}
