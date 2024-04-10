package com.meetup.teame.backend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/healths")
public class HealthCheckController {
    @GetMapping
    public String checkHealth() {
        return "success";
    }
}
