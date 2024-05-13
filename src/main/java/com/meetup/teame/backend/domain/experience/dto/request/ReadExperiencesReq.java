package com.meetup.teame.backend.domain.experience.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor//ModelAttribute 객체는 바인딩과정에서 전체 인자 생성자가 필요함
@Getter
public class ReadExperiencesReq {
    @NotBlank(message = "page is required")
    @Min(value = 0, message = "page must be greater than or equal to 0")
    @Schema(example = "0")
    private Long page;

    @NotBlank(message = "sort is required")
    @Schema(example = "latest")
    private String sort;

    @NotBlank(message = "category is required")
    @Schema(example = "전체")
    private String category;
}
