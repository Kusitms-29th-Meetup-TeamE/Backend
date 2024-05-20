package com.meetup.teame.backend.domain.activity.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReadActivitiesReq {

    @NotBlank(message = "page is required")
    @Min(value = 0, message = "page must be greater than or equal to 0")
    @Schema(example = "0")
    private long page;

    private List<String> agencyTypes = new ArrayList<>();

    private List<String> personalities = new ArrayList<>();
}
