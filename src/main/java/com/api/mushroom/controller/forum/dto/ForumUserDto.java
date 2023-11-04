package com.api.mushroom.controller.forum.dto;

import java.time.LocalDateTime;

public record ForumUserDto(LocalDateTime createdAt,
                            String pseudo,
                            String filename) { }
