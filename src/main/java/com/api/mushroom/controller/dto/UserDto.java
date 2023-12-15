package com.api.mushroom.controller.dto;

import com.api.mushroom.security.Role;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
public class UserDto {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String email;
    private String pseudo;
    private String lastname;
    private String firstname;
    private String password;
    private Role role;
    private Collection<? extends GrantedAuthority> authorities;
    private String filename;
    private boolean isVerified;
    private List<ForumSubjectDto> forumSubjects;
    private List<ForumCommentaryDto> forumCommentaries;
}
