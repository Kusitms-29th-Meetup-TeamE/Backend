package com.meetup.teame.backend.domain.user.dto.response;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class ReadExperienceProfileRes {

    private Long id;

    private String name;

    private Long age;

    private String gender;

    private String location;

    private String area;

    private String introduce;


}
