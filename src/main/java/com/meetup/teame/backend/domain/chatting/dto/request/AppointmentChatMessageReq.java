package com.meetup.teame.backend.domain.chatting.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@Getter
public class AppointmentChatMessageReq {
    @NotBlank(message = "senderId is required")
    private Long senderId;

    @NotBlank(message = "appointmentTime is required")
    private LocalDateTime appointmentTime;

    @NotBlank(message = "location is required")
    private String location;
}
