package com.meetup.teame.backend.domain.experience.controller;

import com.meetup.teame.backend.domain.experience.dto.request.ReadExperiencesReq;
import com.meetup.teame.backend.domain.experience.dto.response.ReadExperiencesRes;
import com.meetup.teame.backend.domain.experience.dto.response.MyExperienceProfileRes;
import com.meetup.teame.backend.domain.experience.service.ExperienceService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "배움나누기 페이지 경험 목록 조회", description = """
            배움 나누기 페이지의 경험 목록을 조회합니다.
            
            jwt토큰도 같이 전달해서 요청해주셔야 합니다.
            
            url 쿼리 파라미터로 다음과 같은 값을 전달해주셔야 합니다.
            
            page : 조회할 페이지 (0페이지 부터 시작)
            
            sort : 정렬 기준 (latest : 최신순, review : 리뷰순)
            
            category : 조회할 카테고리 (전체, 운동, 요리, 예술, 창작, 외국어, 디지털, 기타)
            """)
    @GetMapping
    public ResponseEntity<ReadExperiencesRes> readExperiences(@ModelAttribute @Valid ReadExperiencesReq readExperiencesReq) {
        return ResponseEntity.ok(experienceService.readExperiences(readExperiencesReq));
    }

    @Operation(summary = "배움나누기 페이지 내 경험 조회", description = """
            배움 나누기 페이지의 로그인한 유저의 경험을 조회합니다.
                        
            현재는 임시로 고정된 더미 유저의 데이터를 전달하는 식으로 구현되어 있습니다.
                        
            jwt토큰도 같이 전달해서 요청해주셔야 합니다.
            """)
    @GetMapping("/profile")
    public ResponseEntity<MyExperienceProfileRes> readMyExperienceProfile() {
        return ResponseEntity.ok(experienceService.readMyExperienceProfile());
    }
}
