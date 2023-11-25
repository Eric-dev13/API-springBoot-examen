package com.api.mushroom.controller.edibility;

import java.time.LocalDateTime;

public record EdibilityDto(
        Long id,
        LocalDateTime createdAt,
        String name,
        String filename
) {
}
