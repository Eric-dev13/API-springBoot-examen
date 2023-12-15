package com.api.mushroom.service.user.model;

import java.time.LocalDateTime;

public record ForumCommentaryModel(
        Long id,
        LocalDateTime createdAt,
        String commentary
) {
}
