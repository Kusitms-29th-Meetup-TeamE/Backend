package com.meetup.teame.backend.domain.activity.repository.custom;

import com.meetup.teame.backend.domain.activity.entity.Activity;
import com.meetup.teame.backend.domain.activity.entity.AgencyType;
import com.meetup.teame.backend.domain.personality.Personality;
import com.meetup.teame.backend.domain.user.entity.User;

import java.util.List;

public interface ActivityRepositoryCustom {
    List<Activity> findActivitiesForUser(User user);

    List<Activity> findByAgencyAndPersonalities(long offset, long limit, AgencyType agencyType, List<Personality> personalities);

    List<Activity> findByUserLikesAndAgencyAndPersonalities(Long userId, long offset, long limit, AgencyType agencyType, List<Personality> personalities);

    Long countActivities(AgencyType agencyType, List<Personality> personalities);
}
