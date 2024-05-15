package com.meetup.teame.backend.domain.activity.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.meetup.teame.backend.domain.activity.dto.request.ReadActivitiesReq;
import com.meetup.teame.backend.domain.activity.dto.response.ActivityDetailsRes;
import com.meetup.teame.backend.domain.activity.dto.response.ActivitySummaryRes;
import com.meetup.teame.backend.domain.activity.dto.response.ReadActivitiesRes;
import com.meetup.teame.backend.domain.activity.entity.Activity;
import com.meetup.teame.backend.domain.activity.entity.AgencyType;
import com.meetup.teame.backend.domain.activity.repository.ActivityRepository;
import com.meetup.teame.backend.domain.like.repository.ActivityLikeRepository;
import com.meetup.teame.backend.domain.personality.Personality;
import com.meetup.teame.backend.global.exception.CustomException;
import com.meetup.teame.backend.global.exception.ExceptionContent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ActivityService {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    private final AmazonS3 amazonS3;
    private final ActivityRepository activityRepository;
    private final ActivityLikeRepository activityLikeRepository;

    private static final int ACTIVITY_PAGE_SIZE = 12;

    //활동 참여 신청하기


    //특정 활동 상제 정보 response dto화
    public ActivityDetailsRes getActivityDetails(Long activityId) {
        Activity activity = findActivityById(activityId);
        List<String> imageUrls = getImageUrls(activity);
        return ActivityDetailsRes.of(activity, imageUrls);
    }

    //activityId로 특정 활동 불러오기
    private Activity findActivityById(Long activityId) {
        return activityRepository.findById(activityId)
                .orElseThrow(() -> new CustomException(ExceptionContent.NOT_FOUND_ACTIVITY));
    }

    //활동 목록 필터링으로 조회
    public ReadActivitiesRes findActivities(Long userId, ReadActivitiesReq activitiesReq) {
        long page = activitiesReq.getPage();
        long offset = page * ACTIVITY_PAGE_SIZE;
        long limit = ACTIVITY_PAGE_SIZE;
        AgencyType agencyType = null;
        if (activitiesReq.getAgencyType() != null) {
            agencyType = AgencyType.of(activitiesReq.getAgencyType());
        }
        List<Personality> personalities = activitiesReq.getPersonalities().stream()
                .map(Personality::of)
                .collect(Collectors.toList());
        //long totalCount = activityRepository.countActivities(agencyType, personalities);
        //long pageCount = (totalCount + ACTIVITY_PAGE_SIZE - 1) / ACTIVITY_PAGE_SIZE; // 전체 페이지 수 계산
        long pageCount = activityRepository.countActivities(agencyType, personalities) / ACTIVITY_PAGE_SIZE + 1; // 전체 페이지 수 계산


        List<Long> likedActivityIds = activityLikeRepository.findLikedActivityIdsByUserId(userId);
        Set<Long> likedActivityIdsSet = new HashSet<>(likedActivityIds);

        List<Activity> activities = activityRepository.findByAgencyAndPersonalities(offset, limit, agencyType, personalities);
        List<ActivitySummaryRes> activitySummaries = activities.stream()
                .map(activity -> ActivitySummaryRes.of(activity, likedActivityIdsSet.contains(activity.getId())))
                .toList();


        // 조건에 맞는 활동 조회
        return ReadActivitiesRes.of(page, pageCount, activitySummaries);
    }

    //관심활동 목록 필터링으로 조회
    public ReadActivitiesRes findlikedActivities(Long userId, ReadActivitiesReq activitiesReq) {
        long page = activitiesReq.getPage();
        long offset = page * ACTIVITY_PAGE_SIZE;
        long limit = ACTIVITY_PAGE_SIZE;
        AgencyType agencyType = null;
        if (activitiesReq.getAgencyType() != null) {
            agencyType = AgencyType.of(activitiesReq.getAgencyType());
        }
        List<Personality> personalities = activitiesReq.getPersonalities().stream()
                .map(Personality::of)
                .collect(Collectors.toList());
        //long totalCount = activityRepository.countActivities(agencyType, personalities);
        long pageCount = activityRepository.countActivities(agencyType, personalities) / ACTIVITY_PAGE_SIZE + 1; // 전체 페이지 수 계산

        List<Long> likedActivityIds = activityLikeRepository.findLikedActivityIdsByUserId(userId);
        Set<Long> likedActivityIdsSet = new HashSet<>(likedActivityIds);

        List<Activity> activities = activityRepository.findLikedActivities(userId, offset, limit, agencyType, personalities);
        List<ActivitySummaryRes> activitySummaries = activities.stream()
                .map(activity -> ActivitySummaryRes.of(activity, likedActivityIdsSet.contains(activity.getId())))
                .toList();

        return ReadActivitiesRes.of(page, pageCount, activitySummaries);
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
