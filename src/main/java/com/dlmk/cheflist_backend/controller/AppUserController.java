package com.dlmk.cheflist_backend.controller;

import com.dlmk.cheflist_backend.model.AppUser;
import com.dlmk.cheflist_backend.service.AppUserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/users")
public class AppUserController {

    private final AppUserService appUserService;
    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping()
    public List<AppUser> getAllUsers() {
        return appUserService.getAllUsers();
    }

    @GetMapping("/csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");
    }

    @PostMapping(consumes = "application/json")
    public void createUser(@RequestBody AppUser user) {
        appUserService.createUser(user);
    }
}
