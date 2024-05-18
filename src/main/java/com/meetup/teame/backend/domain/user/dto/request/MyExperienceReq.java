package com.meetup.teame.backend.domain.user.dto.request;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class MyExperienceReq {

    private String title;

    private String experienceType;

    private String detail;

    private String introduce;
}
