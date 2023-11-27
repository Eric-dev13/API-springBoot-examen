package com.api.mushroom.controller.forum.dto;

import java.time.LocalDateTime;

public record ForumUserDto(
        Long id,
        LocalDateTime createdAt,
        String pseudo,
        String filename
) { }
