package com.api.mushroom.service.user;

import java.time.LocalDateTime;

public record ForumSubjectModel(
        Long id,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        String title,
        String description
) { }
