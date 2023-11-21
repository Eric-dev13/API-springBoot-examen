package com.api.mushroom.controller.user;

import java.time.LocalDateTime;

public record ForumCommentaryDto(
        Long id,
        LocalDateTime createdAt,
        String commentary
) {
}
