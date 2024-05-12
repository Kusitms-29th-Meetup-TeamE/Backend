package com.meetup.teame.backend.domain.activity.service;

import com.meetup.teame.backend.domain.activity.dto.response.ActivityDetailsRes;
import com.meetup.teame.backend.domain.activity.dto.response.ActivityPageRes;
import com.meetup.teame.backend.domain.activity.dto.response.ActivitySummaryRes;
import com.meetup.teame.backend.domain.activity.entity.Activity;
import com.meetup.teame.backend.domain.activity.repository.ActivityRepository;
import com.meetup.teame.backend.global.exception.CustomException;
import com.meetup.teame.backend.global.exception.ExceptionContent;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ActivityService {

    private final ActivityRepository activityRepository;

    //활동 참여 신청하기


    //특정 활동 상제 정보 response dto화
    public ActivityDetailsRes getActivityDetails(Long activityId) {
        Activity activity = findActivityById(activityId);
        return ActivityDetailsRes.of(activity);
    }

    //전체 활동 불러오기 dto화
    public ActivityPageRes getActivitySummaries(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Activity> activityPage = activityRepository.findAll(pageable);
        List<ActivitySummaryRes> activitySummaries = activityPage.getContent().stream()
                .map(ActivitySummaryRes::of)
                .collect(Collectors.toList());
        return new ActivityPageRes(activitySummaries, activityPage.getTotalPages(), activityPage.getTotalElements());
    }

    //activityId로 특정 활동 불러오기
    private Activity findActivityById(Long activityId) {
        return activityRepository.findById(activityId)
                .orElseThrow(() -> new CustomException(ExceptionContent.NOT_FOUND_ACTIVITY));
    }

    //전체 활동 불러오기
    private List<Activity> findAllActivities() {
        return activityRepository.findAll();
    }
}
