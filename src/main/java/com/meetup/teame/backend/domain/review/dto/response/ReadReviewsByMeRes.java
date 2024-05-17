package com.meetup.teame.backend.domain.review.dto.response;

import com.meetup.teame.backend.domain.review.entity.Review;
import lombok.*;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
public class ReadReviewsByMeRes {
    private List<ReviewByMeRes> reviews;

    public static ReadReviewsByMeRes of(List<Review> reviews) {
        return ReadReviewsByMeRes.builder()
                .reviews(reviews.stream()
                        .map(ReviewByMeRes::of)
                        .toList())
                .build();
    }
}
