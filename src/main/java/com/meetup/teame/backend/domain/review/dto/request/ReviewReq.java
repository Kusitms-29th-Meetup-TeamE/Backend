package com.meetup.teame.backend.domain.review.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ReviewReq {

    private String description;

    private Long mentorId;

    private Long menteeId;
}
