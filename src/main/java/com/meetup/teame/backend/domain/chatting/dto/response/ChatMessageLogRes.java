package com.meetup.teame.backend.domain.chatting.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
@Builder
@Getter
public class ChatMessageLogRes {
    private List<ChatMessageRes> chatMessageLog;

    public static ChatMessageLogRes of(List<ChatMessageRes> chatMessageLog) {
        return ChatMessageLogRes.builder()
                .chatMessageLog(chatMessageLog)
                .build();
    }
}
