package com.meetup.teame.backend.domain.user.dto.response;

import com.meetup.teame.backend.domain.experience.entity.Experience;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class MyExperienceRes {

    private Long id;

    private String title;

    private String ExperienceType;

    private String description;

    public static MyExperienceRes of(Experience experience) {
        return MyExperienceRes.builder()
                .id(experience.getId())
                .title(experience.getDescription())
                .ExperienceType(experience.getType().getDescription())
                .description(experience.getDetail())
                .build();
    }
}
