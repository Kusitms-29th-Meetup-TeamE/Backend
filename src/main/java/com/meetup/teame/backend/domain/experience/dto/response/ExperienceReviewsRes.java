package com.meetup.teame.backend.domain.experience.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.meetup.teame.backend.domain.experience.entity.ExperienceType;
import com.meetup.teame.backend.domain.experience.entity.Review;
import com.meetup.teame.backend.domain.user.entity.User;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class ExperienceReviewsRes {
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Getter
    @Builder
    public static class ExperienceReview {
        private String title;
        private String content;
        private String name;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd")
        private LocalDate date;

        public static ExperienceReview of(Review review) {
            return ExperienceReview.builder()
                    .title(review.getAppointmentTitle())
                    .content(review.getContent())
                    .name(review.getMentee().getName())
                    .date(review.getReviewDate())
                    .build();
        }
    }

    private String type;
    private List<ExperienceReview> reviews;

    public static ExperienceReviewsRes of(ExperienceType type, User mentor) {
        List<ExperienceReview> reviews = mentor.getReviewsAboutMe().stream()
                .filter(review -> review.getAppointmentType().equals(type))
                .map(ExperienceReview::of)
                .toList();
        return ExperienceReviewsRes.builder()
                .type(type.getDescription())
                .reviews(reviews)
                .build();
    }
}
