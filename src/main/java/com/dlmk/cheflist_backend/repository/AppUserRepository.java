package com.dlmk.cheflist_backend.repository;

import com.dlmk.cheflist_backend.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface AppUserRepository extends JpaRepository<AppUser,Long> {
    List<AppUser> id(Long id);
}
