package com.meetup.teame.backend.domain.experience.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.meetup.teame.backend.domain.experience.entity.Review;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
public class MyReviewRes {

    private Long id;

    private String type;

    private String title;

    private String experienceDetail;

    private String name;

    private String imageUrl;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd")
    private LocalDate appointmentDate;

    private String review;

    public static MyReviewRes of(Review review) {

        return MyReviewRes.builder()
                .id(review.getId())
                .type(review.getAppointmentType().getDescription())
                .title(review.getAppointmentTitle())
                .experienceDetail(review.getAppointmentDetail())
                .name(review.getMentee().getName())
                .imageUrl(review.getMentee().getImageUrl())
                .appointmentDate(review.getReviewDate())
                .review(review.getContent())
                .build();
    }
}
