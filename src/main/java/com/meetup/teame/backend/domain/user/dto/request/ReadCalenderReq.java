package com.meetup.teame.backend.domain.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class ReadCalenderReq {
    @NotBlank(message="year is required.")
    @Schema(example = "2024")
    private Integer year;

    @NotBlank(message="month is required.")
    @Schema(example = "5")
    private Integer month;
}
