package com.meetup.teame.backend.domain.experience.service;

import com.meetup.teame.backend.domain.auth.jwt.SecurityContextProvider;
import com.meetup.teame.backend.domain.experience.dto.request.ReadExperiencesReq;
import com.meetup.teame.backend.domain.experience.dto.response.MyExperienceProfileRes;
import com.meetup.teame.backend.domain.experience.dto.response.ReadExperienceDetailRes;
import com.meetup.teame.backend.domain.experience.dto.response.ReadExperiencesRes;
import com.meetup.teame.backend.domain.experience.entity.Experience;
import com.meetup.teame.backend.domain.experience.entity.Review;
import com.meetup.teame.backend.domain.experience.repository.ExperienceRepository;
import com.meetup.teame.backend.domain.user.entity.Gender;
import com.meetup.teame.backend.domain.user.entity.User;
import com.meetup.teame.backend.domain.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ExperienceServiceTest {
    @Mock
    private ExperienceRepository experienceRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ExperienceService experienceService;

    private static final long testUserId = 100;

    @BeforeEach
    void setUp() {
        SecurityContextProvider.setupSecurityContextForTest(testUserId);
    }

    @Test
    @DisplayName("배움나누기 목록 조회 성공")
    void readExperiences() {
        //given
        ReadExperiencesReq req = new ReadExperiencesReq(0L, "latest", "운동");
        User me = User.of(
                "김철수",
                "test1@test",
                Gender.MALE,
                18L,
                "신대방동"
        );
        User mento = User.of(
                "김멘토",
                "test2@test",
                Gender.MALE,
                18L,
                "신대방동"
        );
        List<Experience> experiences = List.of(
                Experience.of(
                        "축구하실 분!",
                        "운동",
                        "배움나누기 상세 내용",
                        mento
                ),
                Experience.of(
                        "탁구하실 분!",
                        "운동",
                        "배움나누기 상세 내용",
                        mento
                ),
                Experience.of(
                        "농구하실 분!",
                        "운동",
                        "배움나누기 상세 내용",
                        me
                )
        );

        given(experienceRepository.countExperiences(req.getCategory(), me))
                .willReturn((long) experiences.size());
        given(experienceRepository.findExperiencesOrderByLatest(0, 6, req.getCategory(), me))
                .willReturn(experiences);
        given(userRepository.findById(testUserId))
                .willReturn(Optional.of(me));

        //when
        ReadExperiencesRes result = experienceService.readExperiences(req);

        //then
        assertThat(result.getExperiences()).hasSize(3);
        assertThat(result.getExperiences().get(0).getTitle()).isEqualTo("축구하실 분!");
        assertThat(result.getExperiences().get(0).getName()).isEqualTo("김멘토");
    }

    @Test
    @DisplayName("배움나누기 내 프로필 조회 성공")
    void readMyExperienceProfile() {
        //given
        User me = User.of(
                "김철수",
                "test1@test",
                Gender.MALE,
                18L,
                "신대방동"
        );
        Experience myExperience = Experience.of(
                "축구하실 분!",
                "운동",
                "배움나누기 상세 내용",
                me
        );
        me.getExperiences().add(myExperience);

        given(userRepository.findById(testUserId))
                .willReturn(Optional.of(me));

        //when
        MyExperienceProfileRes result = experienceService.readMyExperienceProfile();

        //then
        assertThat(result.getExperiences()).hasSize(1);
        assertThat(result.getName()).isEqualTo("김철수");
    }
}