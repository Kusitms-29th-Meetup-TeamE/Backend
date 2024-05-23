package com.meetup.teame.backend.domain.chatting.repository;

import com.meetup.teame.backend.domain.chatting.entity.DirectChatRoom;
import com.meetup.teame.backend.domain.chatting.repository.custom.DirectChatRoomRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectChatRoomRepository extends JpaRepository<DirectChatRoom, Long>, DirectChatRoomRepositoryCustom {
}
