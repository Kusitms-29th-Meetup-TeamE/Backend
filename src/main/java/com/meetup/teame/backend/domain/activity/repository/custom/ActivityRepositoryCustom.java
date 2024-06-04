package com.meetup.teame.backend.domain.activity.repository.custom;

import com.meetup.teame.backend.domain.activity.entity.Activity;
import com.meetup.teame.backend.domain.activity.entity.AgencyType;
import com.meetup.teame.backend.domain.user.entity.Personality;
import com.meetup.teame.backend.domain.user.entity.User;

import java.util.List;

public interface ActivityRepositoryCustom {
    List<Activity> findActivitiesForUser(User user);

    List<Activity> findByAgencyAndPersonalities(long offset, long limit, User user, List<AgencyType> agencyTypes, List<Personality> personalities);

    List<Activity> findLikedActivities(Long userId, long offset, long limit, List<AgencyType> agencyTypes, List<Personality> personalities);

    Long countActivities(List<AgencyType> agencyTypes, List<Personality> personalities, User user);
}
