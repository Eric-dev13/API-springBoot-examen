package com.api.mushroom.controller.admin.user;

import com.api.mushroom.entity.ForumSubjectEntity;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public record UserDto(
        Long id,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        String email,
        String password,
        Collection<? extends GrantedAuthority> getAuthorities,
        String pseudo,
        String lastname,
        String firstname,
        String filename,
        boolean isVerified
) {
}
