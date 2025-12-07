package com.dlmk.cheflist_backend.security;

import com.dlmk.cheflist_backend.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.security.core.Authentication;

@Component("authz")
@RequiredArgsConstructor
public class AuthorizationService {

    private final RecipeRepository recipeRepository;

    public boolean isRecipeOwner(Long id, Authentication auth){
        if (auth == null) return false;
        String username = auth.getName();
        return recipeRepository.findOwnerUserNameById(id)
                .map(username::equals)
                .orElse(false);
    }

    public boolean isAdmin(Authentication auth){
        return auth != null && auth.getAuthorities()
                .stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
    }
}
