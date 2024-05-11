package com.meetup.teame.backend.domain.chatting.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class TextChatMessageReq {
    @NotBlank(message = "senderId is required")
    private Long senderId;

    @NotBlank(message = "text is required")
    private String text;
}
