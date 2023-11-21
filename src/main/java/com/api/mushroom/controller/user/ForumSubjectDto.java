package com.api.mushroom.controller.user;

import java.time.LocalDateTime;

public record ForumSubjectDto(
        Long id,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        String title,
        String description
) { }
