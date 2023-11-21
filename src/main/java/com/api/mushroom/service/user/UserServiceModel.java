package com.api.mushroom.service.user;

import com.api.mushroom.entity.ForumCommentaryEntity;
import com.api.mushroom.entity.ForumSubjectEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public record UserServiceModel (
     Long id,
     LocalDateTime createdAt,
     LocalDateTime updatedAt,
     String email,
     String pseudo,
     String lastname,
     String firstname,
     Collection<? extends GrantedAuthority> authorities,
     String filename,
     boolean isVerified,
     List<ForumSubjectModel> forumSubjects,
     List<ForumCommentaryModel> forumCommentaries
){ }










