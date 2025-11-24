package com.dlmk.cheflist_backend.controller;

import com.dlmk.cheflist_backend.model.AppUser;
import com.dlmk.cheflist_backend.model.Role;
import com.dlmk.cheflist_backend.repository.AppUserRepository;
import com.dlmk.cheflist_backend.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AppUserRepository appUserRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AppUser body) {
        Authentication auth = authenticationManager
                .authenticate((new UsernamePasswordAuthenticationToken(body.getUsername(), body.getPassword())));
        SecurityContextHolder.getContext().setAuthentication(auth);
        return new ResponseEntity<>("User signed in", HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AppUser body) {
        if (body.getUsername() == null || body.getUsername().isBlank()
        || body.getPassword() == null || body.getPassword().isBlank()) {
            return ResponseEntity.badRequest().body("Username or password is empty");
        }
        if (appUserRepository.existsByUsername(body.getUsername())) {
            return ResponseEntity.status(409).body("Username is already taken");
        }
        String encodedPassword = passwordEncoder.encode(body.getPassword());

        Role role = roleRepository.findByName("USER")
                .orElseGet(() -> roleRepository.save(Role.builder().name("USER").build()));
        AppUser user = AppUser.builder()
                .username(body.getUsername())
                .password(encodedPassword)
                .roles(new ArrayList<>())
                .build();
        user.getRoles().add(role);

        AppUser savedUser = appUserRepository.save(user);
        return ResponseEntity.ok().body("User registered successfully" + savedUser.getUsername());
    }

}
