package com.meetup.teame.backend.domain.experience.dto.response;

import com.meetup.teame.backend.domain.experience.entity.Experience;
import com.meetup.teame.backend.domain.user.entity.User;
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

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Builder
    static class MyExperience {
        private String type;
        private String message;

        public static MyExperience of(Experience experience) {
            return MyExperience.builder()
                    .type(experience.getType().getDescription())
                    .message(experience.getDescription())
                    .build();
        }
    }

    //todo 1+N문제 발생 가능, fetch join 사용해야함
    public static MyExperienceProfileRes of(User user) {
        return MyExperienceProfileRes.builder()
                .imageUrl(user.getImageUrl())
                .name(user.getName())
                .age(user.getAge())
                .gender(user.getGender().getDescription())
                .location(user.getLocation())
                .experiences(user.getExperiences().stream()
                        .map(MyExperience::of)
                        .toList())
                .build();
    }
}
