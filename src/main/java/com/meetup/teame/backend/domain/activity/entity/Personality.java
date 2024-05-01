package com.meetup.teame.backend.domain.activity.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Personality {
    WINDLESS("잔잔한"),
    ;

    private final String description;
}
