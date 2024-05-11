package com.meetup.teame.backend.domain.experience.dto.response;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
public class ExperienceRes {
    private String type;
    private String imageUrl;
    private String name;
    private Long age;
    private String gender;
    private String location;
    private String message;
}
