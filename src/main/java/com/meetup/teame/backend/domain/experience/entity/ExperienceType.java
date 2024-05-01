package com.meetup.teame.backend.domain.experience.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExperienceType {
    WORKOUT("운동"),
    LANGUAGE("언어"),
    PAINTING("그림"),
    COOKING("요리"),
    ;

    private final String description;
}
