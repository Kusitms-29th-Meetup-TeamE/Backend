package com.meetup.teame.backend.domain.experience.repository;

import com.meetup.teame.backend.domain.experience.entity.Experience;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExperienceRepository extends JpaRepository<Experience, Long> {
}