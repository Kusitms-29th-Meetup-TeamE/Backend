package com.meetup.teame.backend.domain.experience.repository;

import com.meetup.teame.backend.domain.experience.entity.Experience;
import com.meetup.teame.backend.domain.experience.repository.custom.ExperienceRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExperienceRepository extends JpaRepository<Experience, Long>, ExperienceRepositoryCustom {
}
