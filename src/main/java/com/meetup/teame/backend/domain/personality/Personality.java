package com.meetup.teame.backend.domain.personality;

import com.meetup.teame.backend.global.exception.CustomException;
import com.meetup.teame.backend.global.exception.ExceptionContent;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum Personality {
    WINDLESS("잔잔한"),//todo 삭제예정
    ACTIVE("활발한"),
    PEACEFUL("평화로운"),
    NATURE_FRIENDLY("자연친화적인"),
    CREATIVE("창의적인"),
    ACADEMIC("학문적인"),
    ARTISTIC("예술적인"),
    LEARNABLE("배울 수 있는");

    private final String description;

    //todo ExperienceType 참고해서 of메서드로 수정
    public static Personality des2enum(String description) {
        return Arrays.stream(Personality.values())
                .filter(personality -> personality.getDescription().equals(description))
                .findFirst()
                .orElseThrow(() -> new CustomException(ExceptionContent.BAD_REQUEST_PERSONALITY));
    }
}
