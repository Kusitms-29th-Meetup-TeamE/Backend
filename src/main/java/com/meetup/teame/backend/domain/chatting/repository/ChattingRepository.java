package com.meetup.teame.backend.domain.chatting.repository;

import com.meetup.teame.backend.domain.chatting.entity.document.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChattingRepository extends MongoRepository<ChatMessage, String>{
    List<ChatMessage> findByChatRoomId(String chatRoomId);
}