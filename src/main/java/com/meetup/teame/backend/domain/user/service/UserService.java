package com.meetup.teame.backend.domain.user.service;

import com.meetup.teame.backend.domain.activity.repository.ActivityRepository;
import com.meetup.teame.backend.domain.experience.repository.ExperienceRepository;
import com.meetup.teame.backend.domain.user.dto.response.ReadMainRes;
import com.meetup.teame.backend.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
