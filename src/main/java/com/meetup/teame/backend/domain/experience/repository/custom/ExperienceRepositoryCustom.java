package com.meetup.teame.backend.domain.experience.repository.custom;

import com.meetup.teame.backend.domain.experience.entity.Experience;
import com.meetup.teame.backend.domain.user.entity.User;

import java.util.List;

public interface ExperienceRepositoryCustom {
    List<Experience> findExperiencesOrderByLatest(long offset,long limit, String category);
    List<Experience> findExperiencesOrderByReview(long offset,long limit, String category);

    Long countExperiences(String category);

    List<Experience> findExperiencesForMain();
}
