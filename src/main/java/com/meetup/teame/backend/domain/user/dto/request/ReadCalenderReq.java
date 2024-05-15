package com.meetup.teame.backend.domain.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ReadCalenderReq {
    @NotBlank(message="year is required.")
    private Integer year;

    @NotBlank(message="month is required.")
    private Integer month;
}
