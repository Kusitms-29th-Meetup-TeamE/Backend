package com.meetup.teame.backend;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Hidden
@RequestMapping("/healths")
public class HealthCheckController {
    @GetMapping
    public String checkHealth() {
        return "success";
    }
}
