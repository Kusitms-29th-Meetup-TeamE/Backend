package com.meetup.teame.backend.domain.chatting.entity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ChatMessageType {
    TEXT("text"),
    EMOTICON("emoticon"),
    APPOINTMENT("appointment"),
    ;

    private final String type;
}
