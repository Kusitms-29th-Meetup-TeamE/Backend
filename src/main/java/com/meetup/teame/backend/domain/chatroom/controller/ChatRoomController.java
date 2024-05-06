package com.meetup.teame.backend.domain.chatroom.controller;

import com.meetup.teame.backend.domain.chatroom.dto.response.GroupChatRoomRes;
import com.meetup.teame.backend.domain.chatroom.dto.response.ReadDirectChatRoomsRes;
import com.meetup.teame.backend.domain.chatroom.dto.response.ReadGroupChatRoomsRes;
import com.meetup.teame.backend.domain.chatroom.service.ChatRoomService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//todo : 채팅방목록 페이지 진입시 프론트쪽에서 연결해야할 웹소켓 토픽(채팅방당 한개씩)리스트를 반환하는 api를 주고 다 sub하게 만들어야 될듯
@RestController
@RequiredArgsConstructor
@RequestMapping("/chatrooms")
@Tag(name = "chatroom", description = "채팅방 관련 api")
public class ChatRoomController {
    private final ChatRoomService chatRoomService;

    @GetMapping("/group")
    public ResponseEntity<ReadGroupChatRoomsRes> readGroupChatRooms() {
        return ResponseEntity
                .ok(chatRoomService.readGroupChatRooms());
    }

    @GetMapping("/direct")
    public ResponseEntity<ReadDirectChatRoomsRes> readDirectChatRooms() {
        return ResponseEntity
                .ok(chatRoomService.readDirectChatRooms());
    }
}
