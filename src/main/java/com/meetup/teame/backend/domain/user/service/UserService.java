package com.meetup.teame.backend.domain.user.service;

import com.meetup.teame.backend.domain.activity.repository.ActivityRepository;
import com.meetup.teame.backend.domain.auth.oauth.dto.CreateUserRequest;
import com.meetup.teame.backend.domain.experience.repository.ExperienceRepository;
import com.meetup.teame.backend.domain.personality.Personality;
import com.meetup.teame.backend.domain.review.dto.response.ReviewRes;
import com.meetup.teame.backend.domain.review.entity.Review;
import com.meetup.teame.backend.domain.review.repository.ReviewRepository;
import com.meetup.teame.backend.domain.user.dto.request.OnboardingReq;
import com.meetup.teame.backend.domain.user.dto.request.UpdateUserReq;
import com.meetup.teame.backend.domain.user.dto.response.ReadMainRes;
import com.meetup.teame.backend.domain.user.dto.response.UserInfoRes;
import com.meetup.teame.backend.domain.user.entity.Gender;
import com.meetup.teame.backend.domain.user.entity.User;
import com.meetup.teame.backend.domain.user.repository.UserRepository;
import com.meetup.teame.backend.global.exception.CustomException;
import com.meetup.teame.backend.global.exception.ExceptionContent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final ActivityRepository activityRepository;
    private final ExperienceRepository experienceRepository;
    private final ReviewRepository reviewRepository;

    public ReadMainRes readMainPage() {
        //todo 현재는 더미 유저지만 추후에는 SecurityContextHolder 정보를 조회해서 유저 정보를 가져와야 함
        User user = userRepository.findById(5L)
                .orElseThrow(() -> new CustomException(ExceptionContent.NOT_FOUND_USER));
        return ReadMainRes.of(
                activityRepository.findActivitiesForUser(user),
                experienceRepository.findAll(),
                2500
        );
    }

    @Transactional
    public User createUser(CreateUserRequest request) {
        int currentYear = LocalDate.now().getYear();
        int birthYear = Integer.parseInt(request.getBirthyear());
        long age = currentYear - birthYear + 1;
        return User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .gender(Objects.equals(request.getGender(), "male") ? Gender.MALE:Gender.FEMALE)
                .age(age)
                .location(request.getLocation())
                .point(0L)
                .build();
    }

    //user info dto화
    public UserInfoRes getUserInfo(Long userId) {
        User user = findById(userId);
        return UserInfoRes.of(user);
    }

    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ExceptionContent.NOT_FOUND_USER));
    }

    @Transactional
    public Long save(User user) {
        User savedUser = userRepository.save(user);
        return savedUser.getId();
    }

    @Transactional
    public UserInfoRes updateUserInfo(Long userId, UpdateUserReq request) {
        User updatedUser = findById(userId);
        updatedUser.update(request);
        return UserInfoRes.of(updatedUser);
    }

    public List<ReviewRes> getMyReviews(Long userId, String type) {
        List<Review> myReviews = reviewRepository.findReviewsByUserId(userId, type);
        List<ReviewRes> reviews = myReviews.stream()
                .map(ReviewRes::of)
                .toList();
        return reviews;
    }

    @Transactional
    public void setUserPersonality(OnboardingReq onboardingReq) {
        //todo 현재는 더미 유저지만 추후에는 SecurityContextHolder 정보를 조회해서 유저 정보를 가져와야 함
        User user = userRepository.findById(5L)
                .orElseThrow(() -> new CustomException(ExceptionContent.NOT_FOUND_USER));
        List<Personality> personalities = onboardingReq.getPersonalities().stream()
                .map(Personality::of)
                .toList();
        user.setPersonalities(personalities);
    }
}
