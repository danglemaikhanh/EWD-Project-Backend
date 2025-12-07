package com.dlmk.cheflist_backend.controller;

import com.dlmk.cheflist_backend.model.AppUser;
import com.dlmk.cheflist_backend.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final AppUserRepository appUserRepository;

    @GetMapping("/me")
    public Map<String, String> me(Authentication auth) {
        return Map.ofEntries(
                Map.entry("username", auth.getName()),
                Map.entry("role", auth.getAuthorities().iterator().next().getAuthority())
        );
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<AppUser> getAllUsers() {
        return appUserRepository.findAll();
    }

}
