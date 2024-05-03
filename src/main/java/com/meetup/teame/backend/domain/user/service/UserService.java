package com.meetup.teame.backend.domain.user.service;

import com.meetup.teame.backend.domain.activity.repository.ActivityRepository;
import com.meetup.teame.backend.domain.experience.repository.ExperienceRepository;
import com.meetup.teame.backend.domain.personality.Personality;
import com.meetup.teame.backend.domain.user.dto.request.OnboardingReq;
import com.meetup.teame.backend.domain.user.dto.response.ReadMainRes;
import com.meetup.teame.backend.domain.user.entity.User;
import com.meetup.teame.backend.domain.user.repository.UserRepository;
import com.meetup.teame.backend.global.exception.CustomException;
import com.meetup.teame.backend.global.exception.ExceptionContent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final ActivityRepository activityRepository;
    private final ExperienceRepository experienceRepository;

    public ReadMainRes readMainPage() {
        return ReadMainRes.of(
                activityRepository.findAll(),
                experienceRepository.findAll(),
                2500
        );
    }

    @Transactional
    public void setUserPersonality(OnboardingReq onboardingReq) {
        //todo 현재는 더미 유저지만 추후에는 SecurityContextHolder 정보를 조회해서 유저 정보를 가져와야 함
        User user = userRepository.findById(5L)
                .orElseThrow(() -> new CustomException(ExceptionContent.NOT_FOUND_USER));
        List<Personality> personalities = onboardingReq.getPersonalities().stream()
                .map(Personality::des2enum)
                .toList();
        user.setPersonalities(personalities);
    }
}
