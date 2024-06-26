package com.meetup.teame.backend.domain.user.service;

import com.meetup.teame.backend.domain.activity.dto.response.ActivitySummaryRes;
import com.meetup.teame.backend.domain.activity.repository.ActivityRepository;
import com.meetup.teame.backend.domain.auth.jwt.SecurityContextProvider;
import com.meetup.teame.backend.domain.auth.oauth.dto.CreateUserRequest;
import com.meetup.teame.backend.domain.chatting.repository.DirectChatRoomRepository;
import com.meetup.teame.backend.domain.chatting.repository.GroupChatRoomRepository;
import com.meetup.teame.backend.domain.chatting.entity.GroupChatRoom;
import com.meetup.teame.backend.domain.experience.entity.Experience;
import com.meetup.teame.backend.domain.experience.repository.ExperienceRepository;
import com.meetup.teame.backend.domain.activity.repository.ActivityLikeRepository;
import com.meetup.teame.backend.domain.user.entity.Personality;
import com.meetup.teame.backend.domain.experience.dto.response.MyReviewRes;
import com.meetup.teame.backend.domain.experience.entity.Review;
import com.meetup.teame.backend.domain.experience.repository.ReviewRepository;
import com.meetup.teame.backend.domain.user.dto.request.MyExperienceReq;
import com.meetup.teame.backend.domain.user.dto.request.OnboardingReq;
import com.meetup.teame.backend.domain.user.dto.request.ReadCalenderReq;
import com.meetup.teame.backend.domain.user.dto.request.UpdateUserReq;
import com.meetup.teame.backend.domain.user.dto.response.*;
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
    private final DirectChatRoomRepository directChatRoomRepository;
    private final GroupChatRoomRepository groupChatRoomRepository;
    private final ReviewRepository reviewRepository;
    private final ActivityLikeRepository activityLikeRepository;

    public ReadMainRes readMainPage() {
        Long userId = 50L;
        if (!SecurityContextProvider.isAnonymousUser())
            userId = SecurityContextProvider.getAuthenticatedUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ExceptionContent.NOT_FOUND_USER));
        return ReadMainRes.of(
                activityRepository.findActivitiesForUser(user),
                experienceRepository.findExperiencesForMain(),
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
                .imageUrl(request.getImageUrl())
                .gender(Objects.equals(request.getGender(), "male") ? Gender.MALE : Gender.FEMALE)
                .age(age)
                .location(request.getLocation())
                .point(0L)
                .build();
    }

    //user info dto화
    public UserInfoRes getUserInfo() {
        Long userId = SecurityContextProvider.getAuthenticatedUserId();
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
    public UserInfoRes updateUserInfo(UpdateUserReq request) {
        Long userId = SecurityContextProvider.getAuthenticatedUserId();
        User updatedUser = findById(userId);
        updatedUser.update(request);
        return UserInfoRes.of(updatedUser);
    }

    //내 후기 조회
    public List<MyReviewRes> getMyReviews(String type) {
        Long userId = SecurityContextProvider.getAuthenticatedUserId();
        List<Review> myReviews = reviewRepository.findReviewsByUserId(userId, type);
        List<MyReviewRes> reviews = myReviews.stream()
                .map(MyReviewRes::of)
                .toList();
        return reviews;
    }

    //내 활동 조회
    public List<ActivitySummaryRes> getMyActivities() {
        Long userId = SecurityContextProvider.getAuthenticatedUserId();
        User user = findById(userId);

        List<GroupChatRoom> groupChatRooms = groupChatRoomRepository.findForUser(user);
        List<ActivitySummaryRes> myActivities = groupChatRooms.stream()
                .map(GroupChatRoom::getActivity)
                .map(activity -> {
                    boolean isLiked = activityLikeRepository.existsByUserIdAndActivityId(userId, activity.getId());
                    return ActivitySummaryRes.of(activity, isLiked);
                })
                .collect(Collectors.toList());

        return myActivities;
    }

    @Transactional
    public void setUserPersonality(OnboardingReq onboardingReq) {
        Long userId = SecurityContextProvider.getAuthenticatedUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ExceptionContent.NOT_FOUND_USER));
        List<Personality> personalities = onboardingReq.getPersonalities().stream()
                .map(Personality::of)
                .toList();
        user.setPersonalities(personalities);
    }

    public UserOnboardingRes getUserPersonalities() {
        Long userId = SecurityContextProvider.getAuthenticatedUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ExceptionContent.NOT_FOUND_USER));

        return UserOnboardingRes.of(user);
    }

    public ReadCalenderRes readCalender(ReadCalenderReq readCalenderReq) {
        Long userId = SecurityContextProvider.getAuthenticatedUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ExceptionContent.NOT_FOUND_USER));
        return ReadCalenderRes.of(
                groupChatRoomRepository.findActivityForUserInMonth(user, readCalenderReq.getYear(), readCalenderReq.getMonth()),
                directChatRoomRepository.findAppointmentForUserInMonth(user, readCalenderReq.getYear(), readCalenderReq.getMonth()),
                groupChatRoomRepository.findAppointmentForUserInMonth(user, readCalenderReq.getYear(), readCalenderReq.getMonth())
        );
    }

    @Transactional
    public String createExperienceProfile(MyExperienceReq req) {
        Long userId = SecurityContextProvider.getAuthenticatedUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ExceptionContent.NOT_FOUND_USER));

        user.setOneWord(req.getIntroduce());
        Experience experience = Experience.of(req.getTitle(), req.getExperienceType(), req.getDetail(), user);
        experienceRepository.save(experience);
        return "배움 프로필 생성 성공";
    }

    public ReadExperienceProfileRes getExperienceProfile() {
        Long userId = SecurityContextProvider.getAuthenticatedUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ExceptionContent.NOT_FOUND_USER));

        List<Experience> experiences = experienceRepository.findAllByUser(user);
        List<MyExperienceRes> myExperienceRes = experiences.stream()
                .map(MyExperienceRes::of)
                .toList();
        return ReadExperienceProfileRes.of(user, myExperienceRes);
    }
}
