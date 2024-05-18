package com.meetup.teame.backend.domain.user.dto.response;

import com.meetup.teame.backend.domain.experience.entity.Experience;
import com.meetup.teame.backend.domain.user.entity.User;
import lombok.*;

import java.util.List;

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

    private List<MyExperienceRes> experiences;

    public static ReadExperienceProfileRes of(User user, List<MyExperienceRes> experienceRes) {
        return ReadExperienceProfileRes.builder()
                .id(user.getId())
                .name(user.getName())
                .age(user.getAge())
                .gender(user.getGender().getDescription())
                .location(user.getLocation())
                .area(user.getLocation())
                .introduce(user.getOneWord())
                .experiences(experienceRes)
                .build();
    }
}
