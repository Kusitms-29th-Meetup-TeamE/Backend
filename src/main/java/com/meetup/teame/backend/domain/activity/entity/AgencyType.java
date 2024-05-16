package com.meetup.teame.backend.domain.activity.entity;

import com.meetup.teame.backend.global.exception.CustomException;
import com.meetup.teame.backend.global.exception.ExceptionContent;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum AgencyType {
    CULTURAL_CENTER("문화센터"),
    WELFARE_CENTER("복지관"),
    CLUB("동호회"),
    VOLUNTEER_WORK("자원봉사"),
    ONE_DAY_CLASS("원데이 클래스"),
    ETC("기타")
    ;

    private final String description;

    public static AgencyType of(String description) {
        for (AgencyType agencyType : AgencyType.values()) {
            if (agencyType.getDescription().equals(description)) {
                return agencyType;
            }
        }
        throw new CustomException(ExceptionContent.BAD_REQUEST_AGENCY_TYPE);
    }

}
