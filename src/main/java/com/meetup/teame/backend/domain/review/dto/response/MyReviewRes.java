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
public class MyReviewRes {
    private Long id;

    private Long menteeId;

    private String menteeName;

    private Long age;

    private String gender;

    private String location;

    private String time;

    private String imageUrl;

    private String experienceType;

    private String title;

    private String description;

    public static MyReviewRes of(Review review) {
        User mentee = review.getMentee().getMentee();

        return MyReviewRes.builder()
                .id(review.getId())
                .menteeId(mentee.getId())
                .menteeName(mentee.getName())
                .age(mentee.getAge())
                .gender(mentee.getGender().getDescription())
                .location(mentee.getLocation())
                .imageUrl(mentee.getImageUrl())
                .experienceType(review.getMentor().getType().getDescription())
                .title(review.getMentor().getDescription())
                .description(review.getDescription())
                .build();
    }
}
