package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/")
    public String home() {
        return "✅ Spring Boot app is running on Render!";
    }

    @GetMapping("/health")
    public String health() {
        return "OK";
    }
}
