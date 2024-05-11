package com.meetup.teame.backend.domain.experience.dto.response;

import lombok.*;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
public class MyExperienceProfileRes {
    private String imageUrl;
    private String name;
    private Long age;
    private String gender;
    private String location;
    private List<MyExperience> experiences;

    static class MyExperience {
        private String type;
        private String message;
    }
}
