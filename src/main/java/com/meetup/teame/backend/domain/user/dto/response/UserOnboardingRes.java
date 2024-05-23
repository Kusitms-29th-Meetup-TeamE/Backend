package com.meetup.teame.backend.domain.user.dto.response;

import com.meetup.teame.backend.domain.user.entity.Personality;
import com.meetup.teame.backend.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class UserOnboardingRes {

    private List<String> personalities;

    public static UserOnboardingRes of(User user) {
        List<String> personalityDescriptions = user.getPersonalities().stream()
                .map(Personality::getDescription)
                .collect(Collectors.toList());

        return UserOnboardingRes.builder()
                .personalities(personalityDescriptions)
                .build();
    }
}
