package com.meetup.teame.backend.domain.user.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Gender {
    FEMALE("여성"),
    MALE("남성");

    private final String description;

}
