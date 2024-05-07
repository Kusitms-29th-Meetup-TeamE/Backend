package com.meetup.teame.backend.domain.user.service;

import com.meetup.teame.backend.domain.activity.repository.ActivityRepository;
import com.meetup.teame.backend.domain.experience.repository.ExperienceRepository;
import com.meetup.teame.backend.domain.user.dto.response.ReadMainRes;
import com.meetup.teame.backend.domain.user.entity.User;
import com.meetup.teame.backend.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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

    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Long save(User user) {
        User savedUser = userRepository.save(user);
        return savedUser.getId();
    }
}
