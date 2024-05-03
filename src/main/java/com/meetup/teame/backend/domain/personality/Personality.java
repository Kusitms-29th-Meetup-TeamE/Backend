package com.meetup.teame.backend.domain.personality;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Personality {
    WINDLESS("잔잔한"),
    ACTIVE("활발한"),
    PEACEFUL("평화로운"),
    NATURE_FRIENDLY("자연친화적인"),
    CREATIVE("창의적인"),
    ACADEMIC("학문적인"),
    ARTISTIC("예술적인"),
    LEARNABLE("배울 수 있는");

    private final String description;
}
