package com.meetup.teame.backend.domain.chatting.controller;

import com.meetup.teame.backend.domain.chatting.dto.request.AppointmentChatMessageReq;
import com.meetup.teame.backend.domain.chatting.dto.request.EmoticonChatMessageReq;
import com.meetup.teame.backend.domain.chatting.dto.request.TextChatMessageReq;
import com.meetup.teame.backend.domain.chatting.dto.response.ChatMessageLogRes;
import com.meetup.teame.backend.domain.chatting.dto.response.ChatMessageRes;
import com.meetup.teame.backend.domain.chatting.service.ChattingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "chatting", description = "채팅 관련 API")
public class ChattingController {
    private final ChattingService chattingService;

    @MessageMapping("/chatting/{chatRoomId}/text")// /app/chatting/5/text 로 메시지가 오면,
    @SendTo("/topic/chatting/{chatRoomId}")// /topic/chatting/5를 구독한 클라이언트들에게 전송
    public ChatMessageRes sendTextChatting(@Payload TextChatMessageReq textChatMessageReq, @DestinationVariable String chatRoomId) {
        return chattingService.sendTextChatting(textChatMessageReq, chatRoomId);
    }

    @MessageMapping("/chatting/{chatRoomId}/emoticon")
    @SendTo("/topic/chatting/{chatRoomId}")
    public ChatMessageRes sendEmoticonChatting(@Payload EmoticonChatMessageReq emoticonChatMessageReq, @DestinationVariable String chatRoomId) {
        return chattingService.sendEmoticonChatting(emoticonChatMessageReq, chatRoomId);
    }

    @MessageMapping("/chatting/{chatRoomId}/appointment")
    @SendTo("/topic/chatting/{chatRoomId}")
    public ChatMessageRes sendAppointmentChatting(@Payload AppointmentChatMessageReq appointmentChatMessageReq, @DestinationVariable String chatRoomId) {
        return chattingService.sendAppointmentChatting(appointmentChatMessageReq, chatRoomId);
    }

    @SubscribeMapping("/chatting/{chatRoomId}")
    public ChatMessageLogRes subscribeChatting(@DestinationVariable String chatRoomId) {
        return chattingService.getChattingLog(chatRoomId);
    }
}
