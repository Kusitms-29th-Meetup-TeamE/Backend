package com.meetup.teame.backend.domain.chatroom.repository;

import com.meetup.teame.backend.domain.chatroom.entity.ChatRoom;
import com.meetup.teame.backend.domain.chatroom.entity.ChatType;
import com.meetup.teame.backend.domain.chatroom.repository.custom.ChatRoomRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long>, ChatRoomRepositoryCustom {
}
