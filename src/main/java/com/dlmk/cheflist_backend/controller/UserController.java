package com.dlmk.cheflist_backend.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {
    @GetMapping("/me")
    public Map<String, String> me(Authentication auth) {
        return Map.of(
                "username", auth.getName()
        );
    }
}
