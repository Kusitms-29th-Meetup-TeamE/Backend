package com.meetup.teame.backend.domain.experience.controller;

import com.meetup.teame.backend.domain.experience.dto.request.ReadExperiencesReq;
import com.meetup.teame.backend.domain.experience.dto.response.ReadExperiencesRes;
import com.meetup.teame.backend.domain.experience.dto.response.MyExperienceProfileRes;
import com.meetup.teame.backend.domain.experience.service.ExperienceService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/experiences")
@Tag(name = "experience", description = "경험 관련 API")
public class ExperienceController {
    private final ExperienceService experienceService;

    //todo Valid 처리 잘되는지 검증
    @GetMapping
    public ResponseEntity<ReadExperiencesRes> readExperiences(@ModelAttribute @Valid ReadExperiencesReq readExperiencesReq) {
        return ResponseEntity.ok(experienceService.readExperiences(readExperiencesReq));
    }

    @GetMapping("/profile")
    public ResponseEntity<MyExperienceProfileRes> readMyExperienceProfile() {
        return ResponseEntity.ok(experienceService.readMyExperienceProfile());
    }
}
