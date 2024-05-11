package com.meetup.teame.backend.domain.activity.controller;

import com.meetup.teame.backend.domain.activity.dto.response.ActivityDetailsRes;
import com.meetup.teame.backend.domain.activity.dto.response.ActivitySummaryRes;
import com.meetup.teame.backend.domain.activity.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ActivityController {

    private final ActivityService activityService;

    //activityId로 특정 활동 불러오기
    @GetMapping("/{activityId}/activity-details")
    public ResponseEntity<ActivityDetailsRes> getActivityDetails(@PathVariable Long activityId) {
        ActivityDetailsRes activityDetailsRes = activityService.getActivityDetails(activityId);
        return ResponseEntity.ok().body(activityDetailsRes);
    }

    /**
     * 페이징 처리 해야함
     */
    //전체 활동 불러오기
    @GetMapping("/activity-summaries")
    public ResponseEntity<List<ActivitySummaryRes>> getActivitySummaries() {
        List<ActivitySummaryRes> activitySummaries = activityService.getActivitySummaries();
        return ResponseEntity.ok().body(activitySummaries);
    }

}
