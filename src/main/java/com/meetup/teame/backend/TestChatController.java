package com.meetup.teame.backend;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestChatController {
    @MessageMapping("/group/{groupId}")// /app/group/5
    @SendTo("/topic/group/{groupId}")// /topic/group/5
    public TestChatRes sendGroupMessage(@Payload TestChatReq testChatReq, @DestinationVariable String groupId) {
        return TestChatRes.of(testChatReq.getMessage());
    }
}
