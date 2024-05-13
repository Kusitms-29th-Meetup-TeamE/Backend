package com.meetup.teame.backend.domain.activity.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.meetup.teame.backend.domain.activity.dto.response.ActivityDetailsRes;
import com.meetup.teame.backend.domain.activity.dto.response.ActivityPageRes;
import com.meetup.teame.backend.domain.activity.dto.response.ActivitySummaryRes;
import com.meetup.teame.backend.domain.activity.entity.Activity;
import com.meetup.teame.backend.domain.activity.repository.ActivityRepository;
import com.meetup.teame.backend.domain.personality.Personality;
import com.meetup.teame.backend.global.exception.CustomException;
import com.meetup.teame.backend.global.exception.ExceptionContent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ActivityService {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    private final AmazonS3 amazonS3;
    private final ActivityRepository activityRepository;

    private static final int ACTIVITY_PAGE_SIZE = 12;

    //활동 참여 신청하기


    //특정 활동 상제 정보 response dto화
    public ActivityDetailsRes getActivityDetails(Long activityId) {
        Activity activity = findActivityById(activityId);
        List<String> imgs = getImageUrls(activity);
        return ActivityDetailsRes.of(activity, imgs);
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

    //활동 목록 필터링으로 조회
    private List<Activity> findActivitiesByAgencyAndPersonality(String agency, Personality personality) {
        return activityRepository.findByAgencyAndPersonality(agency, personality);
    }

    //전체 활동 불러오기
    private List<Activity> findAllActivities() {
        return activityRepository.findAll();
    }


    /*private String getUrl(Activity activity) {
        URL url = amazonS3.getUrl(bucket, activity.getActivityImgs());
        return "" + url;
    }*/
    public List<String> getImageUrls(Activity activity) {
        List<String> activityImgs = activity.getActivityImgs();
        List<String> imageUrls = new ArrayList<>();

        for (String imgKey : activityImgs) {
            // 이미지의 URL을 생성하기 위해 S3에 대한 요청을 생성
            GeneratePresignedUrlRequest urlRequest = new GeneratePresignedUrlRequest(bucket, imgKey);
            /*// 이미지가 다운로드되는 시간(5분)을 설정합니다.
            urlRequest.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 5));*/

            URL url = amazonS3.generatePresignedUrl(urlRequest);

            imageUrls.add(url.toString());
        }

        // 생성된 이미지 URL 리스트를 반환합니다.
        return imageUrls;
    }
}
