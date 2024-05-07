package com.meetup.teame.backend.domain.user.dto.request;

import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class OnboardingReq {
    @Size(min = 1, message = "at least one personality must be selected")
    private List<String> personalities;
}
