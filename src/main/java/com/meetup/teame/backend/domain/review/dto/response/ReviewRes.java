package com.meetup.teame.backend.domain.review.dto.response;

import com.meetup.teame.backend.domain.review.entity.Review;
import com.meetup.teame.backend.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class ReviewRes {

    private Long id;

    private Long mentorId;

    private String mentorName;

    private Long age;

    private String gender;

    private String location;

    private String time;

    private String imageUrl;

    private String experienceType;

    private String title;

    private String description;

    public static ReviewRes of(Review review) {
        User mentor = review.getMentor().getUser();

        return ReviewRes.builder()
                .id(review.getId())
                .mentorId(mentor.getId())
                .mentorName(mentor.getName())
                .age(mentor.getAge())
                .gender(mentor.getGender().getDescription())
                .location(mentor.getLocation())
                .imageUrl(mentor.getImageUrl())
                .experienceType(review.getMentor().getType().getDescription())
                .title(review.getMentor().getDescription())
                .description(review.getDescription())
                .build();
    }
}
