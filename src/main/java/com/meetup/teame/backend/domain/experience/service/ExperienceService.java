package com.meetup.teame.backend.domain.experience.service;

import com.meetup.teame.backend.domain.auth.jwt.SecurityContextProvider;
import com.meetup.teame.backend.domain.experience.dto.request.ReadExperiencesReq;
import com.meetup.teame.backend.domain.experience.dto.response.ReadExperienceDetailRes;
import com.meetup.teame.backend.domain.experience.dto.response.ReadExperiencesRes;
import com.meetup.teame.backend.domain.experience.dto.response.MyExperienceProfileRes;
import com.meetup.teame.backend.domain.experience.entity.Experience;
import com.meetup.teame.backend.domain.experience.repository.ExperienceRepository;
import com.meetup.teame.backend.domain.user.entity.User;
import com.meetup.teame.backend.domain.user.repository.UserRepository;
import com.meetup.teame.backend.global.exception.CustomException;
import com.meetup.teame.backend.global.exception.ExceptionContent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ExperienceService {
    private final ExperienceRepository experienceRepository;
    private final UserRepository userRepository;
    private static final int EXPERIENCE_PAGE_SIZE = 6;

    public ReadExperiencesRes readExperiences(ReadExperiencesReq readExperiencesReq) {
        Long userId = 50L;
        if (!SecurityContextProvider.isAnonymousUser()) {
            userId = SecurityContextProvider.getAuthenticatedUserId();
        }
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ExceptionContent.NOT_FOUND_USER));
        Long page = readExperiencesReq.getPage();
        String sort = readExperiencesReq.getSort();
        String category = readExperiencesReq.getCategory();
        long offset = page * EXPERIENCE_PAGE_SIZE;
        long limit = EXPERIENCE_PAGE_SIZE;
        if (category.equals("전체"))
            category = null;
        long pageCount = (experienceRepository.countExperiences(category, user) + EXPERIENCE_PAGE_SIZE - 1) / EXPERIENCE_PAGE_SIZE;
        if (sort.equals("latest"))
            return ReadExperiencesRes.of(page, pageCount, experienceRepository.findExperiencesOrderByLatest(offset, limit, category, user));
        else if (sort.equals("review"))
            return ReadExperiencesRes.of(page, pageCount, experienceRepository.findExperiencesOrderByReview(offset, limit, category, user));
        else
            throw new CustomException(ExceptionContent.BAD_REQUEST_SORT_TYPE);
    }

    public MyExperienceProfileRes readMyExperienceProfile() {
        Long userId = SecurityContextProvider.getAuthenticatedUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ExceptionContent.NOT_FOUND_USER));
        return MyExperienceProfileRes.of(user);
    }


    public ReadExperienceDetailRes readExperienceDetail(Long experienceId) {
        Experience experience = experienceRepository.findById(experienceId)
                .orElseThrow(() -> new CustomException(ExceptionContent.NOT_FOUND_EXPERIENCE));
        return ReadExperienceDetailRes.of(experience);
    }
}
