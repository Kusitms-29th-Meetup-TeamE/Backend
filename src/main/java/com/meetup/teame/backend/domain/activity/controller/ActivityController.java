package com.meetup.teame.backend.domain.activity.controller;

import com.meetup.teame.backend.domain.activity.dto.response.ActivityDetailsRes;
import com.meetup.teame.backend.domain.activity.dto.response.ActivityPageRes;
import com.meetup.teame.backend.domain.activity.service.ActivityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Tag(name = "activity", description = "활동 관련 api")
public class ActivityController {

    private final ActivityService activityService;

    @Operation(summary = "활동 상세 정보 조회", description = """
            활동 id로 활동의 상세 정보를 조회합니다.
            
            """)
    //activityId로 특정 활동 불러오기
    @GetMapping("/{activityId}/activity-details")
    public ResponseEntity<ActivityDetailsRes> getActivityDetails(@PathVariable Long activityId) {
        ActivityDetailsRes activityDetailsRes = activityService.getActivityDetails(activityId);
        return ResponseEntity.ok().body(activityDetailsRes);
    }

    @Operation(summary = "전체 활동 불러오기", description = """
            활동 참여하기에 들어가면 제일 처음 볼 수 있는 페이지를 위한 기능입니다.
            
            전체 활동들을 페이지 단위로 불러옵니다.
            
            page = 0, size = 12 로 default 값이 설정되어있어 0번 페이지에 12개의 활동들을 불러옵니다.
            """)
    //전체 활동 불러오기
    @GetMapping("/activities")
    public ResponseEntity<ActivityPageRes> getActivitySummaries(@RequestParam(defaultValue = "0") int page,
                                                                @RequestParam(defaultValue = "12") int size)
    {
        ActivityPageRes activityPage = activityService.getActivitySummaries(page, size);
        return ResponseEntity.ok().body(activityPage);
    }

}
