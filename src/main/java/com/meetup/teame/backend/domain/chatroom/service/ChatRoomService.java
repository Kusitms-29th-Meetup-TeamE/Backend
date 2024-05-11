package com.meetup.teame.backend.domain.chatroom.service;

import com.meetup.teame.backend.domain.chatroom.dto.response.DirectChatRoomRes;
import com.meetup.teame.backend.domain.chatroom.dto.response.GroupChatRoomRes;
import com.meetup.teame.backend.domain.chatroom.dto.response.ReadDirectChatRoomsRes;
import com.meetup.teame.backend.domain.chatroom.dto.response.ReadGroupChatRoomsRes;
import com.meetup.teame.backend.domain.chatroom.entity.ChatType;
import com.meetup.teame.backend.domain.chatroom.repository.ChatRoomRepository;
import com.meetup.teame.backend.domain.user.entity.User;
import com.meetup.teame.backend.domain.user.repository.UserRepository;
import com.meetup.teame.backend.global.exception.CustomException;
import com.meetup.teame.backend.global.exception.ExceptionContent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;

    public ReadGroupChatRoomsRes readGroupChatRooms() {
        //todo : 현재는 더미 유저지만 추후에는 SecurityContextHolder 정보를 조회해서 유저 정보를 가져와야 함
        User user = userRepository.findById(5L)
                .orElseThrow(() -> new CustomException(ExceptionContent.NOT_FOUND_USER));
        return ReadGroupChatRoomsRes
                .of(user, chatRoomRepository.findChatRoomsForUser(user, ChatType.GROUP)
                        .stream()
                        .map(GroupChatRoomRes::of)
                        .collect(Collectors.toList()));
    }

    public ReadDirectChatRoomsRes readDirectChatRooms() {
        //todo : 현재는 더미 유저지만 추후에는 SecurityContextHolder 정보를 조회해서 유저 정보를 가져와야 함
        User user = userRepository.findById(5L)
                .orElseThrow(() -> new CustomException(ExceptionContent.NOT_FOUND_USER));
        return ReadDirectChatRoomsRes
                .of(user, chatRoomRepository.findChatRoomsForUser(user, ChatType.DIRECT)
                        .stream()
                        .map(DirectChatRoomRes::of)
                        .collect(Collectors.toList()));
    }
}
