package com.api.mushroom.service.user;

import java.time.LocalDateTime;

public record ForumCommentaryModel(
        Long id,
        LocalDateTime createdAt,
        String commentary
) {
}
