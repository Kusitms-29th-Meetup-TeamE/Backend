package com.meetup.teame.backend.domain.chatroom.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ChatType {
    GROUP("group"),
    DIRECT("direct");

    private final String type;
}
