package com.meetup.teame.backend.domain.experience.entity;

import com.meetup.teame.backend.global.exception.CustomException;
import com.meetup.teame.backend.global.exception.ExceptionContent;
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

    public static ExperienceType of(String description) {
        for (ExperienceType experienceType : ExperienceType.values()) {
            if (experienceType.getDescription().equals(description)) {
                return experienceType;
            }
        }
        throw new CustomException(ExceptionContent.BAD_REQUEST_EXPERIENCE_TYPE);
    }
}
