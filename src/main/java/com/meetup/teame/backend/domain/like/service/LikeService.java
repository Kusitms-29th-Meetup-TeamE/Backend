package com.meetup.teame.backend.domain.like.service;

import com.meetup.teame.backend.domain.activity.entity.Activity;
import com.meetup.teame.backend.domain.activity.repository.ActivityRepository;
import com.meetup.teame.backend.domain.auth.jwt.SecurityContextProvider;
import com.meetup.teame.backend.domain.like.entity.ActivityLike;
import com.meetup.teame.backend.domain.like.repository.ActivityLikeRepository;
import com.meetup.teame.backend.domain.user.entity.User;
import com.meetup.teame.backend.domain.user.repository.UserRepository;
import com.meetup.teame.backend.global.exception.CustomException;
import com.meetup.teame.backend.global.exception.ExceptionContent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class LikeService {

    private final UserRepository userRepository;
    private final ActivityRepository activityRepository;
    private final ActivityLikeRepository activityLikeRepository;

    //활동 좋아요 활성화
    public void activateLike(Long activityId) {
        Long userId = SecurityContextProvider.getAuthenticatedUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ExceptionContent.NOT_FOUND_USER));
        Activity activity = activityRepository.findById(activityId)
                .orElseThrow(() -> new CustomException(ExceptionContent.NOT_FOUND_ACTIVITY));

        ActivityLike like = ActivityLike.of(activity, user);
        activityLikeRepository.save(like);
    }

    //활동 좋아요 비활성화
    public void deactivateLike(Long activityId) {
        Long userId = SecurityContextProvider.getAuthenticatedUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ExceptionContent.NOT_FOUND_USER));
        Activity activity = activityRepository.findById(activityId)
                .orElseThrow(() -> new CustomException(ExceptionContent.NOT_FOUND_ACTIVITY));

        ActivityLike like = activityLikeRepository.findByActivityAndUser(activity, user);
        activityLikeRepository.delete(like);
    }
}
