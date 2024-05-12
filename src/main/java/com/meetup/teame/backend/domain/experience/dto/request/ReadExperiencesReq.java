package com.meetup.teame.backend.domain.experience.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor//ModelAttribute 객체는 바인딩과정에서 전체 인자 생성자가 필요함
@Getter
public class ReadExperiencesReq {
    @NotBlank(message = "page is required")
    private Long page;

    @NotBlank(message = "sort is required")
    private String sort;

    @NotBlank(message = "category is required")
    private String category;
}
