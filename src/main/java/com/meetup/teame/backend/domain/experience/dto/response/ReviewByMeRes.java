package com.meetup.teame.backend.domain.experience.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.meetup.teame.backend.domain.experience.entity.Review;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
public class ReviewByMeRes {
    private Long id;

    private String type;

    private String title;

    private String appointmentLocation;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd")
    private LocalDate appointmentDate;

    private String imageUrl;

    private String name;

    private Long age;

    private String gender;

    private String location;

    private String experienceDetail;

    private String review;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd")
    private LocalDate reviewDate;

    private Boolean isWritten;

    public static ReviewByMeRes of(Review review) {
        return ReviewByMeRes.builder()
                .id(review.getId())
                .type(review.getAppointmentType().getDescription())
                .title(review.getAppointmentTitle())
                .appointmentLocation(review.getAppointment().getAppointmentLocation())
                .appointmentDate(review.getAppointment().getAppointmentDate())
                .imageUrl(review.getMentor().getImageUrl())
                .name(review.getMentor().getName())
                .age(review.getMentor().getAge())
                .gender(review.getMentor().getGender().getDescription())
                .location(review.getMentor().getLocation())
                .experienceDetail(review.getAppointmentDetail())
                .review(review.getContent())
                .reviewDate(review.getReviewDate())
                .isWritten(review.getIsWritten())
                .build();
    }
}
