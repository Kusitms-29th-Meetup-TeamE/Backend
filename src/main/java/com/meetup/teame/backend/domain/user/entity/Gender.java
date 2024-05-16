package com.meetup.teame.backend.domain.user.entity;

import com.meetup.teame.backend.global.exception.CustomException;
import com.meetup.teame.backend.global.exception.ExceptionContent;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Gender {
    FEMALE("여성"),
    MALE("남성");

    private final String description;

    public static Gender of(String description) {
        for (Gender gender : Gender.values()) {
            if (gender.getDescription().equals(description)) {
                return gender;
            }
        }
        throw new CustomException(ExceptionContent.BAD_REQUEST_GENDER);
    }
}
