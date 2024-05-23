package com.meetup.teame.backend.domain.chatting.repository;

import com.meetup.teame.backend.domain.chatting.entity.UserChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserChatRoomRepository extends JpaRepository<UserChatRoom, Long> {
    boolean existsByChatRoomIdAndUserId(Long chatRoomId, Long userId);
}
