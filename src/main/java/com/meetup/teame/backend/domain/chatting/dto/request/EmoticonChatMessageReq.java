package com.meetup.teame.backend.domain.chatting.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EmoticonChatMessageReq {
    @NotBlank(message = "senderId is required")
    private Long senderId;

    @NotBlank(message = "emoticon is required")
    private String emoticon;
}
