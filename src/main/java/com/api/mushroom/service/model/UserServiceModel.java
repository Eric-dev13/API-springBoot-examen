package com.api.mushroom.service.model;


import com.api.mushroom.security.Role;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
public class UserServiceModel {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String email;
    private String pseudo;
    private String lastname;
    private String firstname;
    private Collection<? extends GrantedAuthority> authorities;
    private String filename;
    private String password;
    private boolean isVerified;
    private List<ForumSubjectServiceModel> forumSubjects;
    private List<ForumCategoryServiceModel> forumCommentaries;
}
