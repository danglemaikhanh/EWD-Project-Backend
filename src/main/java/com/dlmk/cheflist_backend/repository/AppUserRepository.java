package com.dlmk.cheflist_backend.repository;

import com.dlmk.cheflist_backend.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser,Long> {
    Optional<AppUser> findByUsername(String userName);
    Boolean existsByUsername(String username);
}
