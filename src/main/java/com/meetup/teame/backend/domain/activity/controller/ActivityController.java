package com.meetup.teame.backend.domain.activity.controller;

import com.meetup.teame.backend.domain.activity.dto.request.ReadActivitiesReq;
import com.meetup.teame.backend.domain.activity.dto.response.ActivityDetailsRes;
import com.meetup.teame.backend.domain.activity.dto.response.ReadActivitiesRes;
import com.meetup.teame.backend.domain.activity.service.ActivityService;
import com.meetup.teame.backend.domain.auth.jwt.SecurityContextProvider;
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
            
            page : 조회할 페이지 (0페이지 부터 시작)
            
            agencyType : 활동 기관 유형 (우선 기관 하나만 입력하는 것으로 만들었습니다.)
            
            personalities : 활동 성격 유형 (복수 선택 가능합니다, String List로 보내주시면 됩니다.)
            """)
    //전체 활동 불러오기
    @GetMapping("/activities")
    public ResponseEntity<ReadActivitiesRes> getActivities(@ModelAttribute ReadActivitiesReq request) {
        ReadActivitiesRes activities = activityService.findActivities(request);
        return ResponseEntity.ok().body(activities);
    }

    @Operation(summary = "관심 활동 목록 조회", description = """
            관심 활동들을 조회하는 api입니다.
            
            userId를 api 주소에 넣어주세요.
            
            관심 활동들을 페이지 단위로 불러옵니다.
            
            page : 조회할 페이지 (0페이지 부터 시작)
            
            agencyType : 활동 기관 유형 (우선 기관 하나만 입력하는 것으로 만들었습니다.)
            
            personalities : 활동 성격 유형 (복수 선택 가능합니다, String List로 보내주시면 됩니다.)
            
            """)
    //관심 활동 불러오기
    @GetMapping("/activities/liked")
    public ResponseEntity<ReadActivitiesRes> getLikedActivities(@ModelAttribute ReadActivitiesReq request) {
        ReadActivitiesRes activities = activityService.findlikedActivities(request);
        return ResponseEntity.ok().body(activities);
    }
}
