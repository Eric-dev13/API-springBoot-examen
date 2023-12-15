package com.api.mushroom.service.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
public class UserServiceModelChangeList {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String email;
    private String pseudo;
    private String lastname;
    private String firstname;
    private Collection<? extends GrantedAuthority> authorities;
    private String filename;
    private boolean isVerified;
    private List<ForumSubjectServiceModelChangeList> forumSubjects;
    private List<ForumCommentaryServiceModelChangeList> forumCommentaries;
}
