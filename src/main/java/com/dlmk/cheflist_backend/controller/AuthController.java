package com.dlmk.cheflist_backend.controller;

import com.dlmk.cheflist_backend.model.AppUser;
import com.dlmk.cheflist_backend.model.Role;
import com.dlmk.cheflist_backend.repository.AppUserRepository;
import com.dlmk.cheflist_backend.repository.RoleRepository;
import com.dlmk.cheflist_backend.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AppUserRepository appUserRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Value("${app.admin-code}")
    private String adminCode;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AppUser body) {
        if (body.getUsername().isEmpty() || body.getPassword().isEmpty()) {
            return ResponseEntity.badRequest().body("Username or password is empty");
        }
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(body.getUsername(), body.getPassword())
            );
            var user = appUserRepository.findByUsername(body.getUsername()).orElseThrow();
            String token = jwtService.generateToken(
                    new org.springframework.security.core.userdetails.User(
                            user.getUsername(),
                            user.getPassword(),
                            authentication.getAuthorities()
                    )
            );
            return ResponseEntity.ok(Map.of(
                    "token", token,
                    "token_type", "Bearer"
            ));
        }catch (BadCredentialsException e) {
            return ResponseEntity.badRequest().body("Invalid username or password");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestHeader(value = "X-Admin-Code", required = false) String code,
            @RequestBody AppUser body) {
        if (body.getUsername() == null || body.getUsername().isBlank()
        || body.getPassword() == null || body.getPassword().isBlank()) {
            return ResponseEntity.badRequest().body("Username or password is empty");
        }
        if (appUserRepository.existsByUsername(body.getUsername())) {
            return ResponseEntity.status(409).body("Username is already taken");
        }
        String encodedPassword = passwordEncoder.encode(body.getPassword());
        Role roleAdmin = roleRepository.findByName("ADMIN")
                .orElseGet(() -> roleRepository.save(Role.builder().name("ADMIN").build()));
        Role role = roleRepository.findByName("USER")
                .orElseGet(() -> roleRepository.save(Role.builder().name("USER").build()));

        boolean isAdmin = (adminCode != null && !adminCode.isBlank() && adminCode.equals(code));
        AppUser user = AppUser.builder()
                .username(body.getUsername())
                .password(encodedPassword)
                .roles(new ArrayList<>())
                .build();
        user.getRoles().add(isAdmin ? roleAdmin : role);

        AppUser savedUser = appUserRepository.save(user);
        return ResponseEntity.created(java.net.URI.create("/api/users" + savedUser.getId()))
                .body("successfully registered " + (isAdmin ? "Admin" : "User"));
    }

}
