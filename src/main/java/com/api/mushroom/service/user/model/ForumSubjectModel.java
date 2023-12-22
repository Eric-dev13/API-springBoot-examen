package com.api.mushroom.service.user.model;

import java.time.LocalDateTime;

public record ForumSubjectModel(
        Long id,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        String title,
        String description
) { }
