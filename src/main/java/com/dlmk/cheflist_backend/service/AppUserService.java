package com.dlmk.cheflist_backend.service;

import com.dlmk.cheflist_backend.model.AppUser;
import com.dlmk.cheflist_backend.repository.AppUserRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AppUserService {

    private final AppUserRepository appUserRepository;

    public AppUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public List<AppUser> getAllUsers() {
        return appUserRepository.findAll();
    }

    public void createUser(AppUser user) {
        appUserRepository.save(user);
    }
}
