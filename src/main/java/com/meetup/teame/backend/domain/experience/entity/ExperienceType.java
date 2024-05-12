package com.meetup.teame.backend.domain.experience.entity;

import com.meetup.teame.backend.global.exception.CustomException;
import com.meetup.teame.backend.global.exception.ExceptionContent;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExperienceType {
    WORKOUT("운동"),
    PAINTING("그림"),//todo 삭제 예정
    COOKING("요리"),
    ART("예술"),
    CREATION("창작"),
    LANGUAGE("외국어"),
    DIGITAL("디지털"),
    ETC("기타")
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
