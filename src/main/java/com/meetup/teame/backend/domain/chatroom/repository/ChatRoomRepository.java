package com.meetup.teame.backend.domain.chatroom.repository;

import com.meetup.teame.backend.domain.chatroom.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long>{
}
