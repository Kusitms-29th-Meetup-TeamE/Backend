package com.meetup.teame.backend.domain.chatroom.controller;

import com.meetup.teame.backend.domain.chatroom.dto.response.ReadDirectChatRoomsRes;
import com.meetup.teame.backend.domain.chatroom.dto.response.ReadGroupChatRoomsRes;
import com.meetup.teame.backend.domain.chatroom.service.ChatRoomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

//todo : 채팅방목록 페이지 진입시 프론트쪽에서 연결해야할 웹소켓 토픽(채팅방당 한개씩)리스트를 반환하는 api를 주고 다 sub하게 만들어야 될듯
@RestController
@RequiredArgsConstructor
@RequestMapping("/chatrooms")
@Tag(name = "chatroom", description = "채팅방 관련 api")
public class ChatRoomController {
    private final ChatRoomService chatRoomService;

    @Operation(summary = "활동 대화방(단체 채팅방) 목록 조회", description = """
            현재 로그인한 유저의 활동 대화방을 조회합니다.
                        
            현재는 임시로 고정된 더미 유저의 데이터를 전달하는 식으로 구현되어 있습니다.
                        
            jwt토큰도 같이 전달해서 요청해주셔야 합니다.
            """)
    @GetMapping("/group")
    public ResponseEntity<ReadGroupChatRoomsRes> readGroupChatRooms() {
        return ResponseEntity
                .ok(chatRoomService.readGroupChatRooms());
    }

    @Operation(summary = "배움나누기 대화방(1:1 대화방) 목록 조회", description = """
            현재 로그인한 유저의 경험나누기 대화방을 조회합니다.
                        
            현재는 임시로 고정된 더미 유저의 데이터를 전달하는 식으로 구현되어 있습니다.
                        
            jwt토큰도 같이 전달해서 요청해주셔야 합니다.
            """)
    @GetMapping("/direct")
    public ResponseEntity<ReadDirectChatRoomsRes> readDirectChatRooms() {
        return ResponseEntity
                .ok(chatRoomService.readDirectChatRooms());
    }

    @Operation(summary = "활동 대화방 참여", description = """
            request header에 jwt토큰을 넣고 url parameter에 활동 id를 넣어 요청하면 대화방에 참여합니다.
            """)
    @PostMapping("/group/{activityId}")
    public ResponseEntity<Void> joinGroupChatRoom(
            @PathVariable Long activityId
    ) {
        Long chatRoomId = chatRoomService.joinGroupChatRoom(activityId);
        return ResponseEntity
                .created(URI.create("/chatting/" + chatRoomId))
                .build();
    }

    @Operation(summary = "배움나누기 대화방 참여", description = """
            request header에 jwt토큰을 넣고 url parameter에 배움(경험) id를 넣어 요청하면 대화방에 참여합니다.
            """)
    @PostMapping("/direct/{experienceId}")
    public ResponseEntity<Void> joinDirectChatRoom(
            @PathVariable Long experienceId
    ) {
        Long chatRoomId = chatRoomService.joinDirectChatRoom(experienceId);
        return ResponseEntity
                .created(URI.create("/chatting/" + chatRoomId))
                .build();
    }
}
