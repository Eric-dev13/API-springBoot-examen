package com.api.mushroom.controller.forum.dto;

import com.api.mushroom.service.forum.model.ForumSubjectServiceModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ForumCommentaryDto(
        LocalDateTime createdAt,
        @NotBlank
        String commentary,
        @NotNull
        ForumUserDto user,
        @NotNull
        ForumSubjectDto forumSubject
) { }
