package com.meetup.teame.backend.domain.experience.dto.response;

import com.meetup.teame.backend.domain.experience.entity.Experience;
import com.meetup.teame.backend.domain.user.entity.User;
import lombok.*;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class ReadExperienceDetailRes {
    private Long id;
    private String imageUrl;
    private String name;
    private Long age;
    private String gender;
    private String location;
    private String oneWord;
    private String title;
    private String content;
    private List<ExperienceReviewsRes> reviews;
    private List<OtherExperience> otherExperiences;

    public static ReadExperienceDetailRes of(Experience experience) {
        User user = experience.getUser();

        List<ExperienceReviewsRes> reviews = user.getExperiences().stream()
                .map(Experience::getType)
                .distinct()
                .map(type -> ExperienceReviewsRes.of(type, user))
                .toList();

        List<OtherExperience> otherExperiences = user.getExperiences().stream()
                .filter(exp -> !exp.getId().equals(experience.getId()))
                .map(OtherExperience::of)
                .toList();

        return ReadExperienceDetailRes.builder()
                .id(experience.getId())
                .imageUrl(user.getImageUrl())
                .name(user.getName())
                .age(user.getAge())
                .gender(user.getGender().getDescription())
                .location(user.getLocation())
                .oneWord(user.getOneWord())
                .title(experience.getDescription())
                .content(experience.getDetail())
                .reviews(reviews)
                .otherExperiences(otherExperiences)
                .build();
    }
}
