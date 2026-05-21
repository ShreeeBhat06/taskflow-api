package com.taskflow.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Health", description = "API health check")
@RestController
public class HealthController {

    @GetMapping("/")
    public String check() {
        return "TaskFlow API is live!";
    }
}